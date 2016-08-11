package com.founder.aliyun.util;

import java.util.Map;

public class GenerateURL {

	public static String gen(String url, Map<String, Object> params) {
		url = url + "?" + DataFormat.toGetParam(params);
		return url;
	}
}
