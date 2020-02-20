package cn.edu.dgut.school_helper.pojo.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO implements Serializable {

	private Integer messageId;
	private String openId;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date date;
	private Integer status;
	private Integer replyId;
	private String fromOpenName;
	private String toOpenName;

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

}
