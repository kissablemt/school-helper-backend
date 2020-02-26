package cn.edu.dgut.school_helper.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.edu.dgut.school_helper.config.WxMaConfiguration;
import cn.edu.dgut.school_helper.constant.JwtRequestConstant;import cn.edu.dgut.school_helper.filter.JwtAccessTokenFilter;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.JwtUtils;
import me.chanjar.weixin.common.error.WxErrorException;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 登陆接口
	 */
	@GetMapping("/login")
	public CommonResponse login(String appid, String code, String signature, String rawData,
			String encryptedData, String iv) {
		if (StringUtils.isBlank(code)) {
			return CommonResponse.error("empty jscode");
		}

		final WxMaService wxService = WxMaConfiguration.getMaService(appid);
		try {
			WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
			String sessionKey = session.getSessionKey();
			if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
				return CommonResponse.error("user check failed");
			}
			// 解密用户信息
			WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
			log.info(userInfo.toString());
			return loginAndGetJwt(userInfo);
		} catch (WxErrorException e) {
			log.error(e.getMessage(), e);
			return CommonResponse.error("出错了");
		}
	}

	@GetMapping("/keepLogin")
	public CommonResponse refreshTokenGetAcceessToken(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		String accessToken = JwtUtils.createAccessToken(openId);
		return CommonResponse.isOk(accessToken);
	}

	
	
	private CommonResponse loginAndGetJwt(WxMaUserInfo userInfo) {
		// TODO 如果是新用户把用户信息存入数据库。登录返回jwt字符串
		User user = new User().setOpenId(userInfo.getOpenId())
				.setNickname(userInfo.getNickName())
				.setHeadPortraitUrl(userInfo.getAvatarUrl())
				.setFaithValue(100)
				.setContactWay("无")
				.setSchoolId(1);
		// 不存在用户
		if (!userService.checkUserExistByOpenId(new User().setOpenId(userInfo.getOpenId()))) {
			if (!userService.registUser(user)) {
				return CommonResponse.error("error");
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put(JwtRequestConstant.ACCESS_TOKNE, JwtUtils.createAccessToken(user.getOpenId()));
		map.put(JwtRequestConstant.REFRESH_TOKNE,JwtUtils.createRefreshToken(user.getOpenId(), redisTemplate));
		
		return CommonResponse.isOk(map);
	}
}
