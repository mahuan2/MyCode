package com.founder.aliyun.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.SliderUI;

import com.founder.aliyun.entry.Asset;
import com.founder.aliyun.util.AliyunClient;

public class Search {

	public static List<Asset> get(Map<String, Object> params) throws InterruptedException {
		String method = "search";
		Object json = AliyunClient.getResult(method, params);
		List<Map<String, Object>> records = (List<Map<String, Object>>) ((Map<String, Object>) json).get("records");
		List<Asset> assets = new LinkedList<Asset>();
		for (Map<String, Object> map : records) {
			Asset asset = new Asset();
			asset.id = (Integer) map.get("id");
			asset.from = (String) map.get("from");
			asset.url = (String) map.get("url");
			asset.pubTime = (String) map.get("pubTime");
			asset.createdAt = (String) map.get("createdAt");
			assets.add(asset);
		}
		Thread.sleep(200);
		return assets;
	}

	public static int getPageCount(Map<String, Object> params) {
		String method = "search";
		Object json = AliyunClient.getOrign(method, params);
		Map<String, Object> map = (Map<String, Object>) json;
		int pageSize = (Integer) map.get("pageSize");
		int totalCount = (Integer) map.get("totalCount");
		return totalCount / pageSize;
	}
}
