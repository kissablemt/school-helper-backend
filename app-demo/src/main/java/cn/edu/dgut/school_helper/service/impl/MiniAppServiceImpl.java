package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.config.WxMaProperties;
import cn.edu.dgut.school_helper.service.MiniAppService;
import cn.edu.dgut.school_helper.util.Base64Utils;
import cn.edu.dgut.school_helper.util.OnlineUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MiniAppServiceImpl implements MiniAppService {

    @Autowired
    private WxMaProperties miniappProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MiniAppServiceImpl.class);

    private static final String MINI_APP_ACCESS_TOKEN = "access_token";

    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String VALIDATE_SENSITIVE_CONTENT = "https://api.weixin.qq.com/wxa/msg_sec_check";

    private static final String VALIDATE_SENSITIVE_IMAGE = "https://api.weixin.qq.com/wxa/img_sec_check";

    @Override
    public String getValidateSensitiveContentToken() {
        // 缓存中获取accessToken
        if (redisTemplate.hasKey(MINI_APP_ACCESS_TOKEN)) {
            return redisTemplate.opsForValue().get(MINI_APP_ACCESS_TOKEN);
        }
        // 所需参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", miniappProperties.getConfigs().get(0).getAppid());
        params.put("secret", miniappProperties.getConfigs().get(0).getSecret());
        params.put("grant_type", "client_credential");
        // 发送请求
        String responseStr = OnlineUtils.sendGet(GET_ACCESS_TOKEN_URL, params);
        Map response = null;
        try {
            response = objectMapper.readValue(responseStr, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            log.error("解析返回的json字符串错误");
            e.printStackTrace();
        }
        // 放入缓存后返回
        String accessToken = (String) response.get(MINI_APP_ACCESS_TOKEN);
        long expiresIn = ((Integer) response.get("expires_in")).longValue();
        redisTemplate.opsForValue().set(MINI_APP_ACCESS_TOKEN, accessToken, expiresIn, TimeUnit.SECONDS);
        return accessToken;
    }

    @Override
    public Boolean validateSensitiveWords(String content) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MiniAppServiceImpl.MINI_APP_ACCESS_TOKEN, getValidateSensitiveContentToken());
        // 验证敏感信息
        Map<String, String> contentMap = new HashMap<String, String>();
        contentMap.put("content", content);
        try {
            content = objectMapper.writeValueAsString(contentMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String responseStr = OnlineUtils.sendPostContent(VALIDATE_SENSITIVE_CONTENT, params, content);
        if (StringUtils.contains(responseStr, "87014")) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean validateSensitiveImage(String[] imgBase64Strs) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(MINI_APP_ACCESS_TOKEN, getValidateSensitiveContentToken());
        // 验证敏感信息
        for (String base64Str : imgBase64Strs) {
            byte[] bytes = Base64Utils.decode(base64Str);
            String responseStr = null;
            responseStr = OnlineUtils.sendPostImage(VALIDATE_SENSITIVE_IMAGE, params, bytes);
            System.out.println(responseStr);
            if (StringUtils.contains(responseStr, "87014")) {
                return false;
            }
        }
        return true;
    }
}
