package cn.edu.dgut.school_helper.config.response;

/**
 *  用于抛出业务处理异常
 * @author 星星星
 *
 */
public class ServiceRuntimeExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620218678949183946L;

	public ServiceRuntimeExecption(String message) {
		super(message);
	}

}
