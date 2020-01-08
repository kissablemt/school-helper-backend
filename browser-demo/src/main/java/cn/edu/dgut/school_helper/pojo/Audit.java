package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`audit`")
public class Audit implements Serializable {
    @Id
    @Column(name = "`audit_id`")
    private Integer auditId;

    /**
     * 举报id
     */
    @Column(name = "`report_id`")
    private Integer reportId;

    /**
     * 管理员id
     */
    @Column(name = "`admin_id`")
    private Integer adminId;

    /**
     * 处理时间
     */
    @Column(name = "`date`")
    private Date date;

    /**
     * 审核状态：1-未审核，2-举报失败，3-举报成功
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * @return audit_id
     */
    public Integer getAuditId() {
        return auditId;
    }

    /**
     * @param auditId
     */
    public Audit setAuditId(Integer auditId) {
        this.auditId = auditId;
        return this;
    }

    /**
     * 获取举报id
     *
     * @return report_id - 举报id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置举报id
     *
     * @param reportId 举报id
     */
    public Audit setReportId(Integer reportId) {
        this.reportId = reportId;
        return this;
    }

    /**
     * 获取管理员id
     *
     * @return admin_id - 管理员id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员id
     *
     * @param adminId 管理员id
     */
    public Audit setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    /**
     * 获取处理时间
     *
     * @return date - 处理时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置处理时间
     *
     * @param date 处理时间
     */
    public Audit setDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * 获取审核状态：1-未审核，2-举报失败，3-举报成功
     *
     * @return status - 审核状态：1-未审核，2-举报失败，3-举报成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置审核状态：1-未审核，2-举报失败，3-举报成功
     *
     * @param status 审核状态：1-未审核，2-举报失败，3-举报成功
     */
    public Audit setStatus(Integer status) {
        this.status = status;
        return this;
    }
}