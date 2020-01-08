package cn.edu.dgut.school_helper.util;

import java.io.Serializable;

/**
 * <b>Description:</b><br>
 * 
 * @author <a href="www.mtproject.cn" target="_blank">Monty</a>
 * @version 1.0
 * @Note <b>ProjectName:</b> web-demo <br>
 *       <b>PackageName:</b> net.seehope.demo.util <br>
 *       <b>ClassName:</b> CommonResponse <br>
 *       <b>Date:</b> Jul 15, 2019 4:00:14 PM
 */
public class CommonResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 表示当前相应的状态是成功或者失败
     */
    private Boolean status;
    /**
     * 表示当响应失败之后给前端的错误提示
     */
    private String msg;
    /**
     * 表示当响应成功之后返回给前端的数据
     */
    private Object data;

    public static CommonResponse isOk(Object data) {
        return new CommonResponse(true, "请求成功", data);
    }

    public static CommonResponse error(String msg) {
        return new CommonResponse(false, msg, null);
    }

    /**
     * 
     */
    public CommonResponse() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param status
     * @param msg
     * @param data
     */
    public CommonResponse(Boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
