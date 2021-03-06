package cn.edu.dgut.school_helper.pojo.dto;

import cn.edu.dgut.school_helper.pojo.Image;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PostOutputDTO implements Serializable {

	private Integer postId;
	private String openId;
	private String headPortraitUrl;
	private String nickname;
	private String contactWay;
	private Integer faithValue;
	private String headline;
	private String content;
	private BigDecimal money;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date date;
	private Integer goodsType;
	private Integer postType;
	private List<Image> images;

	private static final long serialVersionUID = 1L;

	/**
	 * 1-发布中，2-被封禁,3-被删除
	 */
	private Integer status;

	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getPostType() {
		return postType;
	}
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public Integer getFaithValue() { return faithValue; }
	public void setFaithValue(Integer faithValue) { this.faithValue = faithValue; }
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}


}
