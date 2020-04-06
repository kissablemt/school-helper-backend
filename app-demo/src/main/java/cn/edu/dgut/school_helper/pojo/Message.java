package cn.edu.dgut.school_helper.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`message`")
public class Message implements Serializable {
    /**
     * 系统消息id
     */
    @Id
    @Column(name = "`message_id`")
    private Integer messageId;

    /**
     * 收消息者id
     */
    @Column(name = "`open_id`")
    private String openId;

    /**
     * 消息内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 发送时间
     */
    @Column(name = "`date`")
    private Date date;

    /**
     * 评论id
     */
    @Column(name = "`reply_id`")
    private Integer replyId;

    /**
     * 1-未读，2-已读
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取系统消息id
     *
     * @return message_id - 系统消息id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * 设置系统消息id
     *
     * @param messageId 系统消息id
     */
    public Message setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    /**
     * 获取收消息者id
     *
     * @return open_id - 收消息者id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置收消息者id
     *
     * @param openId 收消息者id
     */
    public Message setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 获取消息内容
     *
     * @return content - 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     *
     * @param content 消息内容
     */
    public Message setContent(String content) {
        this.content = content;
		return this;
    }

    /**
     * 获取发送时间
     *
     * @return date - 发送时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置发送时间
     *
     * @param date 发送时间
     */
    public Message setDate(Date date) {
        this.date = date;
        return this;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public Message setReplyId(Integer replyId) {
        this.replyId = replyId;
        return this;
    }

    /**
     * 获取1-未读，2-已读
     *
     * @return status - 1-未读，2-已读
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1-未读，2-已读
     *
     * @param status 1-未读，2-已读
     */
    public Message setStatus(Integer status) {
        this.status = status;
        return this;
    }
}