package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`collection`")
public class Collection implements Serializable {
    @Id
    @Column(name = "`collection_id`")
    private Integer collectionId;

    /**
     * 被收藏的帖子id
     */
    @Column(name = "`post_id`")
    private Integer postId;

    /**
     * 收藏者id
     */
    @Column(name = "`open_id`")
    private String openId;

    private static final long serialVersionUID = 1L;

    /**
     * @return collection_id
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * @param collectionId
     */
    public Collection setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    /**
     * 获取被收藏的帖子id
     *
     * @return post_id - 被收藏的帖子id
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置被收藏的帖子id
     *
     * @param postId 被收藏的帖子id
     */
    public Collection setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    /**
     * 获取收藏者id
     *
     * @return open_id - 收藏者id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置收藏者id
     *
     * @param openId 收藏者id
     */
    public Collection setOpenId(String openId) {
        this.openId = openId;
		return this;
    }
}