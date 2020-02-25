package cn.edu.dgut.school_helper.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.edu.dgut.school_helper.config.WxMaConfiguration;
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
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 登陆接口
	 */
	@GetMapping("/login")
	public CommonResponse login(@PathVariable String appid, String code, String signature, String rawData,
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
			return CommonResponse.isOk(loginAndGetJwt(userInfo));
		} catch (WxErrorException e) {
			log.error(e.getMessage(), e);
			return CommonResponse.error("出错了");
		}
	}

	private String loginAndGetJwt(WxMaUserInfo userInfo) {
		// TODO 如果是新用户把用户信息存入数据库。登录返回jwt字符串
		User user = new User().setOpenId(userInfo.getOpenId())
				.setNickname(userInfo.getOpenId())
				.setHeadPortraitUrl(userInfo.getAvatarUrl())
				.setFaithValue(100)
				.setContactWay("无");
		//		.setSchoolId(null)
		// 不存在用户
		if (!userService.checkUserExistByOpenId(new User().setOpenId(userInfo.getOpenId()))) {
			if (!userService.registUser(user)) {
				return "error";
			}
		}
		return JwtUtils.createAccessToken(user.getOpenId());
	}
}
