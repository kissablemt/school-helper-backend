package cn.edu.dgut.school_helper.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`admin`")
public class Admin implements Serializable {
    @Id
    @Column(name = "`admin_id`")
    private Integer adminId;

    /**
     * 用户名
     */
    @Column(name = "`username`")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 角色
     */
    @Column(name = "`role`")
    private Integer role;

    private static final long serialVersionUID = 1L;

    /**
     * @return admin_id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public Admin setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public Admin setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * 获取角色
     *
     * @return role - 角色
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置角色
     *
     * @param role 角色
     */
    public Admin setRole(Integer role) {
        this.role = role;
		return this;
    }
}