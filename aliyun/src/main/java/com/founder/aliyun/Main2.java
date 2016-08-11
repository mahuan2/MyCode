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
public class Main2 {

	public static void main(String[] args) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageSize", "100");
		params.put("pubTimeBegin", "2016-08-08%2023:59:59");
		params.put("pubTimeEnd", "2016-08-09%2023:59:59");
		// 获取数据
		MyWriter writer = new MyWriter("all");
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
	}
}