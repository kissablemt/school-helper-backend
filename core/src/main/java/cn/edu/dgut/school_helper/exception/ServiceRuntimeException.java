package cn.edu.dgut.school_helper.exception;

/**
 *  用于抛出业务处理异常
 * @author 星星星
 *
 */
public class ServiceRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620218678949183946L;

	public ServiceRuntimeException(String message) {
		super(message);
	}

}
