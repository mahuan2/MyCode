package com.founder.aliyun;

import java.io.File;
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
import com.founder.aliyun.util.MyWriter;

@SuppressWarnings("restriction")
public class AliDownload {

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
					params.put("spiderTopicId", spiderTopic.id);
					params.put("monitorKeywordId", keyword.id);
					params.put("pageSize", "100");
					params.put("pubTimeBegin", "2016-08-10%2023:59:59");
					params.put("pubTimeEnd", "2016-08-11%2023:59:59");
					// 获取数据
					MyWriter writer = new MyWriter(topic.name + File.separator + keyword.keyword + "_" + spiderTopic.name);
					int pageCount = Search.getPageCount(params);
					System.out.println(pageCount);
					for (int i = 1; i <= pageCount + 1; i++) {
						params.put("toPage", i);
						List<Asset> assets = Search.get(params);
						for (Asset asset : assets) {
							if (asset.monitorKeywords.equals(keyword.keyword)) {
								writer.writeLine(asset.from.trim() + " " + asset.url.trim() + " " + asset.createdAt.trim() + " " + asset.pubTime.trim());
							}
						}
						params.remove("toPage");
					}
					writer.close();
					params.remove("spiderTopic");
					params.remove("monitorKeywordId");
					params.remove("pageSize");
					params.remove("pubTimeBegin");
					params.remove("pubTimeEnd");
				}
			}
		}
	}
}