package cn.edu.dgut.school_helper.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.UserLog;
import cn.edu.dgut.school_helper.service.UserLogService;

@Component
@Aspect
public class UserLogAspect {

	@Autowired
	private UserLogService userLogService;

	private static final Logger log = LoggerFactory.getLogger(UserLogAspect.class);

	// .. 当前包及子包路径
	@Pointcut("execution(* cn.edu.dgut.school_helper..service.impl..*.*(..)) && !execution(* cn.edu.dgut.school_helper..service.impl.UserLogServiceImpl.*(..))")
	public void pointcut() {
	}

	@AfterReturning(value = "pointcut()", argNames = "joinPoint,result", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		
		Object[] args = joinPoint.getArgs();
		String methodName = joinPoint.getSignature().getName();

		String openId = (String) request.getAttribute(JwtRequestConstant.OPEN_ID);
		Integer opType = getOptionType(methodName);
		String opContent = optionContent(args, methodName);
		
		log.info(opContent);
		// 记录日志
		UserLog userLog = new UserLog();
		userLog.setOpenId(openId);
		userLog.setContent(opContent);
		userLog.setOperationType(opType);
		userLog.setDate(new Date());

		log.info(ReflectionToStringBuilder.toString(userLog, ToStringStyle.MULTI_LINE_STYLE));
		userLogService.addUserLog(userLog);
	}

	private Integer getOptionType(String methodName) {

		Integer opType = null;
		if (methodName.startsWith("select")) {
			opType = LogConstanst.SELECT;
		} else if (methodName.startsWith("add")) {
			opType = LogConstanst.ADD;
		} else if (methodName.startsWith("update")) {
			opType = LogConstanst.UPDATE;
		} else if (methodName.startsWith("delete")) {
			opType = LogConstanst.DELETE;
		} else {
			opType = LogConstanst.UNKNOW;
		}
		return opType;
	}
	/**
	 * 浅层层遍历对象属性，记录日志
	 * @param args
	 * @param mName
	 * @return
	 */
	private String optionContent(Object[] args, String mName) {
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		if (args == null) {
			return rs.toString();
			
		}
		// 遍历参数对象
		for (int i = 0; i < args.length; i++) {
			// 获取对象类型
			Object arg = args[i];
			Class<? extends Object> clazz = arg.getClass();
			String className = clazz.getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[param" + (i+1) + "，type=" + className + "，value=");
			
			//判断是否为基本数据类型
			if(isPrimitive(arg)) {
				rs.append(arg + "]");
				continue;
			}
			// 遍历方法get,获取所有属性值
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				//非get方法忽略
				String methodName = method.getName();
				if (methodName.indexOf("get") == -1) {
					continue;
				
				}
				//获取返回值
				Object rsValue = null;
				try {
					rsValue = method.invoke(arg);
				} catch (Exception e) {
					continue;
				
				}
				// 将值加入内容中
				rs.append("(" + methodName + "=" + rsValue + ")");
			}
			rs.append("]");
		}
		return rs.toString();
	}
	
	private boolean isPrimitive(Object obj) {
		try {
			return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}

}
