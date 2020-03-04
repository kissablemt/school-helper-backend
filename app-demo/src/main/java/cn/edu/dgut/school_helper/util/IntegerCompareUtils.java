package cn.edu.dgut.school_helper.util;

public class IntegerCompareUtils {
	
	public static Boolean equals(Integer num, Integer num2) {
		if(num == null || num2 == null) {
			return false;
		}
		return num.equals(num2);
	}
	
}
