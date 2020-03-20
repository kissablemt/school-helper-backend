package cn.edu.dgut.school_helper.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`reply`")
public class Reply implements Serializable {
    /**
     * 评论id
     */
    @Id
    @Column(name = "`reply_id`")
    private Integer replyId;

    /**
     * 帖子id
     */
    @Column(name = "`post_id`")
    private Integer postId;

    /**
     * 评论内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 评论时间
     */
    @Column(name = "`date`")
    private Date date;

    /**
     * 父评论id
     */
    @Column(name = "`parent_id`")
    private Integer parentId;

    @Column(name = "`from_open_id`")
    private String fromOpenId;

    @Column(name = "`to_open_id`")
    private String toOpenId;

    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取评论id
     *
     * @return reply_id - 评论id
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 设置评论id
     *
     * @param replyId 评论id
     */
    public Reply setReplyId(Integer replyId) {
        this.replyId = replyId;
        return this;
    }

    /**
     * 获取帖子id
     *
     * @return post_id - 帖子id
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置帖子id
     *
     * @param postId 帖子id
     */
    public Reply setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public Reply setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取评论时间
     *
     * @return date - 评论时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置评论时间
     *
     * @param date 评论时间
     */
    public Reply setDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * 获取父评论id
     *
     * @return parent_id - 父评论id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父评论id
     *
     * @param parentId 父评论id
     */
    public Reply setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * @return from_open_id
     */
    public String getFromOpenId() {
        return fromOpenId;
    }

    /**
     * @param fromOpenId
     */
    public Reply setFromOpenId(String fromOpenId) {
        this.fromOpenId = fromOpenId;
        return this;
    }

    /**
     * @return to_open_id
     */
    public String getToOpenId() {
        return toOpenId;
    }

    /**
     * @param toOpenId
     */
    public Reply setToOpenId(String toOpenId) {
        this.toOpenId = toOpenId;
        return this;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public Reply setStatus(Integer status) {
        this.status = status;
		return this;
    }
}