package cn.edu.dgut.school_helper.pojo.dto;

import java.io.Serializable;

public class PostQueryDTO implements Serializable{
	
	/**
	 * 1-二手书，2-二手车，3-数码，4-家电，5-其他
	 */
	private Integer goodsType;
	/**
	 * 1-卖，2-求购，3-失物招领，4-义捐活动
	 */
	private Integer postType;
	private Integer pageNum;
	private Integer pageSize;
	
	private static final long serialVersionUID = 1L;
	
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
