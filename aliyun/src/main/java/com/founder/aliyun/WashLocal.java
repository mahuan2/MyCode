package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.founder.aliyun.util.MyWriter;

public class WashLocal {
	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\Users\\mahuan\\Desktop\\WEIBO\\20160812093049_5965490\\Main");
		MyWriter writer = new MyWriter(dir.getParent() + File.separator + "out");
		int count = 0;
		Set<String> sets = new HashSet<String>();
		File[] files = dir.listFiles();
		for (File file : files) {
			FileReader reader = new FileReader(file);
			BufferedReader reader2 = new BufferedReader(reader);
			Set<String> set = new HashSet<String>(20000);
			String line = "";
			String from = "";
			String url = "";
			String createAt = "";
			String pubTime = "";
			boolean isStart = false;
			boolean isEnd = true;
			while ((line = reader2.readLine()) != null) {
				if (line.startsWith("%&")) {
					isStart = true;
					isEnd = false;
				}
				else if (line.startsWith("&%")) {
					isStart = false;
					isEnd = true;
					count++;
					writer.writeLine(from + " " + url + " " + createAt + " " + pubTime);
				}
				if (isStart && !isEnd) {
					if (line.startsWith("SN")) {
						from = line.substring(3).replace(" ", "_");
					}
					else if (line.startsWith("ST")) {
						url = line.substring(3).replace(":80", "").replace(" ", "_");
						sets.add(url);
					}
					else if (line.startsWith("RQ")) {
						pubTime = line.substring(3).replace(" ", "T").replace(" ", "_");
					}
					else if (line.startsWith("CT")) {
						createAt = line.substring(3).replace(" ", "T").replace(" ", "_");
					}
				}
			}
			reader2.close();
		}

		writer.close();
		System.out.println(sets.size());
		System.out.println(count);
		System.out.println(count - sets.size());
	}
}
