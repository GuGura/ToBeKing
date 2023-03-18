package util;

public class IsWrite {
	
	public static boolean isWrite(String... str) {
		for(int i = 0 ; i<str.length ;i++) {
			if(str[i] == null || str[i].trim().length() == 0) {
				return true;
			}
		}
		return false;
	}
	public static boolean isWrite1(String... str) {
		for(int i = 0 ; i<str.length ;i++) {
			if(str[i] == null) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWrite(int... num) {
		for(int i = 0 ; i<num.length ;i++) {
			if(num[i] == 0) {
				return true;
			}
		}
		return false;
	}
}
