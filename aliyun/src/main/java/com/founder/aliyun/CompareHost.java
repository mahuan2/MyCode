package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CompareHost {
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("C:\\Users\\mahuan\\git\\aliyun\\论坛ali");
		BufferedReader reader1 = new BufferedReader(reader);
		reader = new FileReader("C:\\Users\\mahuan\\git\\aliyun\\论坛fou");
		BufferedReader reader2 = new BufferedReader(reader);
		Set<String> set1 = new HashSet<String>(20000);
		Set<String> set2 = new HashSet<String>(20000);
		String line = "";
		while ((line = reader1.readLine()) != null) {
			set1.add(line.split(" ")[0]);
		}
		while ((line = reader2.readLine()) != null) {
			set2.add(line.split(" ")[0]);
		}
		int countAli = 0;
		int countFo = 0;
		int countAll = 0;
		for (String s1 : set1) {
			if (!set2.contains(s1)) {
				countAli++;
				System.out.println(s1);
			}
			if (set2.contains(s1)) {
				countAll++;
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
