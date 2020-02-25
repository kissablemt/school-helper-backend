package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`school`")
public class School implements Serializable {
    @Id
    @Column(name = "`school_id`")
    private Integer schoolId;

    /**
     * 学校名称
     */
    @Column(name = "`school_name`")
    private String schoolName;

    /**
     * 学校logo地址
     */
    @Column(name = "`school_logo_url`")
    private String schoolLogoUrl;

    private static final long serialVersionUID = 1L;

    /**
     * @return school_id
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * @param schoolId
     */
    public School setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    /**
     * 获取学校名称
     *
     * @return school_name - 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置学校名称
     *
     * @param schoolName 学校名称
     */
    public School setSchoolName(String schoolName) {
        this.schoolName = schoolName;
		return this;
    }

    /**
     * 获取学校logo地址
     *
     * @return school_logo_url - 学校logo地址
     */
    public String getSchoolLogoUrl() {
        return schoolLogoUrl;
    }

    /**
     * 设置学校logo地址
     *
     * @param schoolLogoUrl 学校logo地址
     */
    public School setSchoolLogoUrl(String schoolLogoUrl) {
        this.schoolLogoUrl = schoolLogoUrl;
        return this;
    }
}