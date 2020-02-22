package cn.edu.dgut.school_helper.pojo.dto;

import java.io.Serializable;

public class ReportDTO implements Serializable {

	private Integer reportId;
	private Integer postId;
	/**
	 * 帖子的标题
	 */
	private String headline;
	private String remark;
	private String reporterId;
	private Integer status;

	private static final long serialVersionUID = 1L;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
