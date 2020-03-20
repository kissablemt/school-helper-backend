package cn.edu.dgut.school_helper.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`user_log`")
public class UserLog implements Serializable {
    @Id
    @Column(name = "`log_id`")
    private Integer logId;

    /**
     * 用户id
     */
    @Column(name = "`open_id`")
    private String openId;

    /**
     * 操作内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 操作类型：crud
     */
    @Column(name = "`operation_type`")
    private Integer operationType;

    /**
     * 操作时间
     */
    @Column(name = "`date`")
    private Date date;

    private static final long serialVersionUID = 1L;

    /**
     * @return log_id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public UserLog setLogId(Integer logId) {
        this.logId = logId;
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
    public UserLog setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 获取操作内容
     *
     * @return content - 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作内容
     *
     * @param content 操作内容
     */
    public UserLog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取操作类型：crud
     *
     * @return operation_type - 操作类型：crud
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型：crud
     *
     * @param operationType 操作类型：crud
     */
    public UserLog setOperationType(Integer operationType) {
        this.operationType = operationType;
		return this;
    }

    /**
     * 获取操作时间
     *
     * @return date - 操作时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置操作时间
     *
     * @param date 操作时间
     */
    public UserLog setDate(Date date) {
        this.date = date;
        return this;
    }
}