package cn.edu.dgut.school_helper.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "`user`")
public class User implements Serializable {
    @Id
    @Column(name = "`open_id`")
    private String openId;

    /**
     * 头像地址
     */
    @Column(name = "`head_portrait_url`")
    private String headPortraitUrl;

    /**
     * 用户名
     */
    @Column(name = "`nickname`")
    private String nickname;

    /**
     * 联系方式
     */
    @Column(name = "`contact_way`")
    private String contactWay;

    /**
     * 信誉值
     */
    @Column(name = "`faith_value`")
    private Integer faithValue;

    /**
     * 学校id
     */
    @Column(name = "`school_id`")
    private Integer schoolId;

    private static final long serialVersionUID = 1L;

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public User setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 获取头像地址
     *
     * @return head_portrait_url - 头像地址
     */
    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    /**
     * 设置头像地址
     *
     * @param headPortraitUrl 头像地址
     */
    public User setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
        return this;
    }

    /**
     * 获取用户名
     *
     * @return nickname - 用户名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户名
     *
     * @param nickname 用户名
     */
    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    /**
     * 获取联系方式
     *
     * @return contact_way - 联系方式
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * 设置联系方式
     *
     * @param contactWay 联系方式
     */
    public User setContactWay(String contactWay) {
        this.contactWay = contactWay;
        return this;
    }

    /**
     * 获取信誉值
     *
     * @return faith_value - 信誉值
     */
    public Integer getFaithValue() {
        return faithValue;
    }

    /**
     * 设置信誉值
     *
     * @param faithValue 信誉值
     */
    public User setFaithValue(Integer faithValue) {
        this.faithValue = faithValue;
        return this;
    }

    /**
     * 获取学校id
     *
     * @return school_id - 学校id
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * 设置学校id
     *
     * @param schoolId 学校id
     */
    public User setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
		return this;
    }
}