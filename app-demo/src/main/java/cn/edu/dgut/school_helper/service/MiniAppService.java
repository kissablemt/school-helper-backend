package cn.edu.dgut.school_helper.service;

public interface MiniAppService {

    /**
     * 微信小程序绝大多数接口都需要该token
     */
    String getValidateSensitiveContentToken();
    /**
     * 敏感词判断
     */
    Boolean validateSensitiveWords(String content);

    /**
     * 敏感图片判断
     */
    Boolean validateSensitiveImage(String[] imgBase64Strs);

}
