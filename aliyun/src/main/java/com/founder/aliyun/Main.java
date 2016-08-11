package com.founder.aliyun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.aliyun.entry.Asset;
import com.founder.aliyun.entry.Keyword;
import com.founder.aliyun.entry.SpiderTopic;
import com.founder.aliyun.entry.Topic;
import com.founder.aliyun.service.GetKeywords;
import com.founder.aliyun.service.QueryTopicList;
import com.founder.aliyun.service.Search;
import com.founder.aliyun.util.JsonTools;
import com.founder.aliyun.util.MyWriter;
import com.founder.aliyun.util.Page;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		List<Topic> topics = QueryTopicList.get(params);
		// 获取topic
		for (Topic topic : topics) {
			System.out.println("Topic :" + topic.id + " " + topic.name);
			params.put("topicId", topic.id);
			List<Keyword> keywords = GetKeywords.get(params);
			params.remove("topicId");
			// 获取keyword
			for (Keyword keyword : keywords) {
				System.out.println("Keyword :" + keyword.id + " " + keyword.keyword);
				// 获取spiderTopic（数据源）
				for (SpiderTopic spiderTopic : keyword.spiderTopics) {
					System.out.println("SpiderTopic :" + spiderTopic.id + " " + spiderTopic.name);
					if (!spiderTopic.name.contains("微博"))
						continue;
					params.put("spiderTopicId", spiderTopic.id);
					params.put("pageSize", "100");
					params.put("pubTimeBegin", "2016-08-08%2023:59:59");
					params.put("pubTimeEnd", "2016-08-09%2023:59:59");
					// 获取数据
					MyWriter writer = new MyWriter(topic.name + "_" + keyword.keyword + "_" + spiderTopic.name);
					int pageCount = Search.getPageCount(params);
					System.out.println(pageCount);
					for (int i = 1; i <= pageCount + 1; i++) {
						params.put("toPage", i);
						List<Asset> assets = Search.get(params);
						for (Asset asset : assets) {
							writer.writeLine(asset.from + " " + asset.url + " " + asset.createdAt + " " + asset.pubTime);
						}
					}
					writer.close();
					params.remove("spiderTopic");
					params.remove("pageSize");
				}
			}
		}
	}
}