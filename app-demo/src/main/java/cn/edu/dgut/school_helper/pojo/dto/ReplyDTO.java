package cn.edu.dgut.school_helper.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


public class ReplyDTO {

    private Integer replyId;
    private Integer postId;
    private String content;
    private Integer parentId;
    private String fromOpenId;
    private String toOpenId;
    private String fromOpenName;
    private String fromHeadPortraitUrl;
    private String toHeadPortraitUrl;
    private String toOpenName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer status;
    List<ReplyDTO> replys;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFromOpenId() {
        return fromOpenId;
    }

    public void setFromOpenId(String fromOpenId) {
        this.fromOpenId = fromOpenId;
    }

    public String getToOpenId() {
        return toOpenId;
    }

    public void setToOpenId(String toOpenId) {
        this.toOpenId = toOpenId;
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

	public List<ReplyDTO> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplyDTO> replys) {
        this.replys = replys;
    }

}
