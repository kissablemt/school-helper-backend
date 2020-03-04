package cn.edu.dgut.school_helper.config.response;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import cn.edu.dgut.school_helper.util.CommonResponse;

//@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

	public static final String  RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";
	
	private static final Logger log = LoggerFactory.getLogger(ResponseResultHandler.class);
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		//判断请求是否有包装标记
		ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
		return responseResultAnn == null ? false : true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		//不处理异常
		//已经被包装过了，那么不管（异常或者接口使用者直接包装）
		if(body instanceof CommonResponse) {
			return body;
		}
		return CommonResponse.isOk(body);
	}
	
	
	
	/**
	 * 处理所有未知异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CommonResponse handleException(Exception e) {
		log.info(e.getMessage());
		return CommonResponse.error("操作失败");
	}
	
	/**
	 * 处理业务异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceRuntimeExecption.class)
	@ResponseBody
	public CommonResponse handleServiceRumtimeExecption(ServiceRuntimeExecption e) {
		log.info(e.getMessage());
		return CommonResponse.error(e.getMessage());
	}
	
}
