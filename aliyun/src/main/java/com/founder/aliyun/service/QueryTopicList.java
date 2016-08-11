package com.founder.aliyun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.aliyun.config.Config;
import com.founder.aliyun.entry.Topic;
import com.founder.aliyun.util.AliyunClient;
import com.founder.aliyun.util.DataFormat;
import com.founder.aliyun.util.JsonTools;

public class QueryTopicList {

	public static List<Topic> get(Map<String, Object> params) {
		String method = "queryTopicsList";
		List<Map<String, Object>> list = (List<Map<String, Object>>) AliyunClient.getResult(method, params);
		List<Topic> lll = new ArrayList<Topic>();
		for (Map<String, Object> map : list) {
			Topic topic = new Topic();
			topic.id = (Integer) map.get("id");
			topic.name = (String) map.get("name");
			lll.add(topic);
		}
		return lll;
	}
}
