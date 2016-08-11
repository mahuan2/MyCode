package com.founder.aliyun.entry;

import java.util.ArrayList;
import java.util.List;

public class Keyword {
	public Integer id;
	public String keyword;
	public Integer topicId;
	public String createAt;
	public String updateAt;
	public List<SpiderTopic> spiderTopics = new ArrayList<SpiderTopic>();
}
