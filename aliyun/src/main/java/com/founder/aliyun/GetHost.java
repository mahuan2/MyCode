package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.ULongLongSeqHelper;

import com.founder.aliyun.util.MyWriter;

public class GetHost {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("C:\\Users\\mahuan\\git\\aliyun\\近期热点_奥运 金牌_论坛"); 
		BufferedReader reader1 = new BufferedReader(reader);
		reader = new FileReader("C:\\Users\\mahuan\\git\\aliyun\\论坛");
		BufferedReader reader2 = new BufferedReader(reader);
		Set<String> set1 = new HashSet<String>(20000);
		Set<String> set2 = new HashSet<String>(20000);
		String line = "";
		while ((line = reader1.readLine()) != null) {
			String url = line.trim().replace("  ", " ").replace("  ", " ").split(" ")[1];
			if (!url.startsWith("http"))
				continue;
			URL u = new URL(url);
			set1.add(u.getHost());
		}
		while ((line = reader2.readLine()) != null) {
			String url = line.trim().split(" ")[1];
			if (!url.startsWith("http"))
				continue;
			URL u = new URL(url);
			set2.add(u.getHost());
		}
		MyWriter wr1 = new MyWriter("论坛ali");
		for (String url : set1)
			wr1.writeLine(url);
		wr1.close();
		MyWriter wr2 = new MyWriter("论坛fou");
		for (String url : set2)
			wr2.writeLine(url);
		wr2.close();
		int countAli = 0;
		int countFo = 0;
		int countAll = 0;
		for (String s1 : set1) {
			if (!set2.contains(s1))
				countAli++;
			if (set2.contains(s1)) {
				countAll++;
				System.out.println(s1);
			}
		}
		for (String s2 : set2) {
			if (!set1.contains(s2))
				countFo++;

		}
		System.out.println(set1.size());
		System.out.println(set2.size());
		System.out.println(countAli);
		System.out.println(countFo);
		System.out.println(countAll);
	}
}
