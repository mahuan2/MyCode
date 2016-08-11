package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Analyze {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("D:\\workspace\\aliyun\\近期热点_奥运 金牌_微博");
		BufferedReader reader2 = new BufferedReader(reader);
		Set<String> set = new HashSet<String>(20000);
		String line = "";
		while ((line = reader2.readLine()) != null) {
			set.add(line.split(" ")[1]);
		}
		System.out.println(set.size());
	}
}
