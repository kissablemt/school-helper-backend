package cn.edu.dgut.school_helper.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class MessageDTO implements Serializable {

	private Integer messageId;
	private String openId;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date date;
	private Integer status;
	private Integer replyId;
	private Integer postId;
	private String headline;
	private String fromOpenName;
	private String fromHeadPortraitUrl;
	private String toOpenName;
	private String toHeadPortraitUrl;
	private Integer replyParentId;

	private static final long serialVersionUID = 1L;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getPostId() { return postId; }

	public void setPostId(Integer postId) { this.postId = postId; }

	public String getHeadline() { return headline; }

	public void setHeadline(String headline) { this.headline = headline; }

	public String getFromOpenName() {
		return fromOpenName;
	}

	public void setFromOpenName(String fromOpenName) {
		this.fromOpenName = fromOpenName;
	}

	public String getToOpenName() {
		return toOpenName;
	}

	public void setToOpenName(String toOpenName) {
		this.toOpenName = toOpenName;
	}

	public String getFromHeadPortraitUrl() { return fromHeadPortraitUrl; }

	public void setFromHeadPortraitUrl(String fromHeadPortraitUrl) { this.fromHeadPortraitUrl = fromHeadPortraitUrl; }

	public String getToHeadPortraitUrl() { return toHeadPortraitUrl; }

	public void setToHeadPortraitUrl(String toHeadPortraitUrl) { this.toHeadPortraitUrl = toHeadPortraitUrl; }

	public Integer getReplyParentId() { return replyParentId; }

	public void setReplyParentId(Integer replyParentId) { this.replyParentId = replyParentId; }
}
