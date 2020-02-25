package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`report`")
public class Report implements Serializable {
    @Id
    @Column(name = "`report_id`")
    private Integer reportId;

    /**
     * 帖子id
     */
    @Column(name = "`post_id`")
    private Integer postId;

    /**
     * 举报说明
     */
    @Column(name = "`remark`")
    private String remark;

    /**
     * 举报者id
     */
    @Column(name = "`reporter_id`")
    private String reporterId;

    /**
     * 1-未处理，2-已处理
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * @return report_id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * @param reportId
     */
    public Report setReportId(Integer reportId) {
        this.reportId = reportId;
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
    public Report setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    /**
     * 获取举报说明
     *
     * @return remark - 举报说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置举报说明
     *
     * @param remark 举报说明
     */
    public Report setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 获取举报者id
     *
     * @return reporter_id - 举报者id
     */
    public String getReporterId() {
        return reporterId;
    }

    /**
     * 设置举报者id
     *
     * @param reporterId 举报者id
     */
    public Report setReporterId(String reporterId) {
        this.reporterId = reporterId;
        return this;
    }

    /**
     * 获取1-未处理，2-已处理
     *
     * @return status - 1-未处理，2-已处理
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1-未处理，2-已处理
     *
     * @param status 1-未处理，2-已处理
     */
    public Report setStatus(Integer status) {
        this.status = status;
		return this;
    }
}