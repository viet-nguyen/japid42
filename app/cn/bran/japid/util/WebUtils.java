package cn.bran.japid.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;

import play.i18n.Messages;

public class WebUtils {
    public static String fastformat(Date date, String pattern) {
    	return JapidDateFormat.getInstance(pattern).format(date);
    }
    

	// Note, the {@code JavaExtensions} class in Play! has lots of formatting methods
	public static String numOf(Collection c) {
		if (c == null)
			return "no";
		else
			return String.valueOf(c.size());
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static boolean asBoolean(Object o) {
		if (o == null)
			return false;
		
		boolean r = false;

		if (o instanceof Boolean) {
			r = (Boolean) o;
		} else if (o instanceof Integer) {
			Integer n = (Integer) o;
			r = n != 0 ? true : false;
		} else if (o instanceof Long) {
			Long n = (Long) o;
			r = n != 0 ? true : false;
		}
		else if (o instanceof Collection){
			Collection col = ((Collection)o);
			if (col.size() > 0)
				return true;
			else
				return false;
		}
		else if (o.getClass().isArray()) {
			return Array.getLength(o) > 0;
		}
		else if (o instanceof String) {
			return ((String)o).length() > 0;
		}
		// anything more?
		else {
			r = o != null ? true : false;
		}

		return r;
	}

	public static String getMessage(String key) {
		return Messages.get(key);
	}

	public static String getMessage(String key, String sub) {
		String m = Messages.get(key);
		if (m == null)
			m = sub;
		return m;
	}

	public static String i18n(String key) {
		return getMessage(key);
	}
	
	public static String i18n(String key, String sub) {
		return getMessage(key, sub);
	}
}
