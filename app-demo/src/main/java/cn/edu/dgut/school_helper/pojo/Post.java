package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`post`")
public class Post implements Serializable {
	
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "`post_id`")
    private Integer postId;

    /**
     * 用户id
     */
    @Column(name = "`open_id`")
    private String openId;

    /**
     * 标题
     */
    @Column(name = "`headline`")
    private String headline;

    /**
     * 内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 出售价格、收购价格
     */
    @Column(name = "`money`")
    private BigDecimal money;

    @Column(name="`date`")
    private Date date;
    
    
    @Column(name = "`goods_type`")
    private Integer goodsType;

    @Column(name = "`post_type`")
    private Integer postType;

    /**
     * 1-发布中，2-被封禁,3-被删除
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * @return post_id
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * @param postId
     */
    public Post setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    /**
     * 获取用户id
     *
     * @return open_id - 用户id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户id
     *
     * @param openId 用户id
     */
    public Post setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 获取标题
     *
     * @return headline - 标题
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * 设置标题
     *
     * @param headline 标题
     */
    public Post setHeadline(String headline) {
        this.headline = headline;
        return this;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取出售价格、收购价格
     *
     * @return money - 出售价格、收购价格
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置出售价格、收购价格
     *
     * @param money 出售价格、收购价格
     */
    public Post setMoney(BigDecimal money) {
        this.money = money;
        return this;
    }

    /**
     * @return goods_type
     */
    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * @param goodsType
     */
    public Post setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
        return this;
    }

    /**
     * @return post_type
     */
    public Integer getPostType() {
        return postType;
    }

    /**
     * @param postType
     */
    public Post setPostType(Integer postType) {
        this.postType = postType;
        return this;
    }

    public Date getDate() {
		return date;
	}

	public Post setDate(Date date) {
		this.date = date;
		return this;
	}

	/**
     * 获取1-发布中，2-被封禁,3-被删除
     *
     * @return status - 1-发布中，2-被封禁,3-被删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1-发布中，2-被封禁,3-被删除
     *
     * @param status 1-发布中，2-被封禁,3-被删除
     */
    public Post setStatus(Integer status) {
        this.status = status;
		return this;
    }

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", openId=" + openId + ", headline=" + headline + ", content=" + content
				+ ", money=" + money + ", date=" + date + ", goodsType=" + goodsType + ", postType=" + postType
				+ ", status=" + status + "]";
	}
}