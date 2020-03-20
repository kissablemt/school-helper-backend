package cn.edu.dgut.school_helper.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "`image`")
public class Image implements Serializable {
    @Id
    @Column(name = "`image_id`")
    private Integer imageId;

    /**
     * 图片地址
     */
    @Column(name = "`image_url`")
    private String imageUrl;

    /**
     * 图片顺序
     */
    @Column(name = "`order`")
    private Integer order;

    /**
     * 帖子id
     */
    @Column(name = "`post_id`")
    private Integer postId;

    private static final long serialVersionUID = 1L;

    /**
     * @return image_id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * @param imageId
     */
    public Image setImageId(Integer imageId) {
        this.imageId = imageId;
        return this;
    }

    /**
     * 获取图片地址
     *
     * @return image_url - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public Image setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    /**
     * 获取图片顺序
     *
     * @return order - 图片顺序
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置图片顺序
     *
     * @param order 图片顺序
     */
    public Image setOrder(Integer order) {
        this.order = order;
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
    public Image setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }
}