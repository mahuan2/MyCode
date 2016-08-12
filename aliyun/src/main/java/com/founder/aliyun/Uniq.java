package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.founder.aliyun.util.MyWriter;

public class Uniq {
	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\Users\\mahuan\\git\\aliyun\\近期热点");
		for (File file : dir.listFiles()) {
			System.out.println(file.getName());
			FileReader reader = new FileReader(file);
			BufferedReader reader2 = new BufferedReader(reader);
			Set<String> set = new HashSet<String>(20000);
			String line = "";
			while ((line = reader2.readLine()) != null) {
				set.add(line.split(" ")[1]);
			}
			System.out.println(file.getName() + ":" + set.size());
			reader2.close();
			reader.close();
			MyWriter writer = new MyWriter(file.getCanonicalPath() + "_uniq");
			for (String s : set) {
				writer.writeLine(s);
			}
			writer.close();
		}
	}
}
