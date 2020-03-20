package cn.edu.dgut.school_helper.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.edu.dgut.school_helper.config.WxMaConfiguration;
import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.MiniAppService;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;
import cn.edu.dgut.school_helper.util.JsonResult;
import cn.edu.dgut.school_helper.util.JwtUtils;
import cn.edu.dgut.school_helper.util.OnlineUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Autowired
    private MiniAppService miniAppService;


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public JsonResult login(String appid, String code, String signature, String rawData, String encryptedData,
                            String iv) {
        if (StringUtils.isBlank(code)) {
            return JsonResult.errorMsg("empty jscode");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            String sessionKey = session.getSessionKey();
            if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                return JsonResult.errorMsg("user check failed");
            }
            // 解密用户信息
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            log.info(userInfo.toString());
            return loginAndGetJwt(userInfo);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return JsonResult.errorMsg("出错了");
        }
    }

    @GetMapping("/keepLogin")
    public JsonResult keepLogin(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        String accessToken = JwtUtils.createAccessToken(openId);
        return JsonResult.ok(accessToken);
    }

    @GetMapping("/getUserInfo")
    public JsonResult getUserInfo(@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return JsonResult.ok(userService.getUserInfo(new User().setOpenId(openId)));

    }

    private JsonResult loginAndGetJwt(WxMaUserInfo userInfo) {

        String accessToken = JwtUtils.createAccessToken(userInfo.getOpenId());
        // 存在用户
        if (userService.checkUserExistByOpenId(new User().setOpenId(userInfo.getOpenId())).isOK()) {
            return JsonResult.ok(accessToken);
        }
        //上传头像
        byte[] bytes = OnlineUtils.getImageBytes(userInfo.getAvatarUrl());
        String headUrl = fastDFSClientWrapper.uploadFile(bytes, bytes.length, "jpg");
        //保存到数据库
        User user = new User().setOpenId(userInfo.getOpenId())
                .setNickname(userInfo.getNickName())
                .setHeadPortraitUrl(headUrl)
                .setFaithValue(100)
                .setSchoolId(1);
        if (!userService.addUser(user).isOK()) {
            return JsonResult.errorMsg("error");
        }

        return JsonResult.ok(accessToken);
    }

    @PutMapping
    private JsonResult updateUser(@RequestBody User user, @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return userService.updateUser(user.setOpenId(openId));
    }

    @PutMapping("/headPortrait")
    private JsonResult updateHeadPortrait(@RequestBody Map headPortraitMap, @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        String headPortrait  = (String) headPortraitMap.get("headPortrait");
        String[] strs = new String[]{headPortrait};
        if(!miniAppService.validateSensitiveImage(strs)){
            return JsonResult.errorMsg("含有敏感图片");
        }
        return userService.updateHeadPortrait(openId, headPortrait);
    }

}
