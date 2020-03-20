package cn.edu.dgut.school_helper.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PostInputDTO {

	private Integer postId;
	private String openId;
	private String headline;
	private String content;
	private BigDecimal money;
	private Date date;
	private Integer goodsType;
	private Integer postType;
	private String images;
	
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
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
}
