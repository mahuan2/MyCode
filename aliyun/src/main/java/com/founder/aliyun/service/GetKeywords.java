package com.founder.aliyun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.aliyun.config.Config;
import com.founder.aliyun.entry.Keyword;
import com.founder.aliyun.entry.SpiderTopic;
import com.founder.aliyun.entry.Topic;
import com.founder.aliyun.util.AliyunClient;
import com.founder.aliyun.util.DataFormat;
import com.founder.aliyun.util.JsonTools;

public class GetKeywords {

	public static List<Keyword> get(Map<String, Object> params) {
		String method = "getKeywords";
		List<Map<String, Object>> list = (List<Map<String, Object>>) AliyunClient.getResult(method, params);
		List<Keyword> lll = new ArrayList<Keyword>();
		for (Map<String, Object> map : list) {
			Keyword keyword = new Keyword();
			keyword.id = (Integer) map.get("id");
			keyword.keyword = (String) map.get("keyword");
			keyword.topicId = (Integer) map.get("topicId");
			List<Map<String, Object>> spiderTopics_tmp = (List<Map<String, Object>>) map.get("spiderTopics");
			List<SpiderTopic> spiderTopics = new ArrayList<SpiderTopic>();
			for (Map<String, Object> mm : spiderTopics_tmp) {
				SpiderTopic spiderTopic = new SpiderTopic();
				spiderTopic.id = (Integer) mm.get("id");
				spiderTopic.name = (String) mm.get("name");
				spiderTopics.add(spiderTopic);
			}
			keyword.spiderTopics = spiderTopics;
			lll.add(keyword);
		}
		return lll;
	}
}
