package com.founder.aliyun.util;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class DataFormat {
	public static String toGetParam(Map<String, Object> map) {
		StringBuilder ret = new StringBuilder();
		for (Entry<String, Object> entry : map.entrySet()) {
			ret.append(entry.getKey() + "=" + entry.getValue().toString() + "&");
		}
		if (ret.length() == 0)
			return "";
		ret.deleteCharAt(ret.length() - 1);
		return ret.toString();
//		return URLEncoder.encode(ret.toString());
	}
}
