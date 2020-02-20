package cn.edu.dgut.school_helper.pojo.dto;

import java.io.Serializable;
import java.util.Date;

public class CollectionDTO implements Serializable{

	private Integer collectionId;
	private Integer postId;
	private String headline;
	/**
	 * 帖子首张图片地址
	 */
	private String imageUrl;
	/**
	 * 发帖时间
	 */
	private Date date;
	private String openId;
	private Integer status;
	
	private static final long serialVersionUID = 1L;
	
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
