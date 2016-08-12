package com.founder.aliyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.founder.aliyun.util.MyWriter;

public class FreqCount {
	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\Users\\mahuan\\git\\aliyun\\近期热点");
		for (File file : dir.listFiles()) {
			if (!file.getName().endsWith("uniq"))
				continue;
			FileReader reader = new FileReader(file);
			BufferedReader reader2 = new BufferedReader(reader);
			HashMap<String, Integer> set = new HashMap<String, Integer>(20000);
			String line = "";
			while ((line = reader2.readLine()) != null) {
				String url = line.trim().replace("  ", " ").replace("  ", " ");
				if (!url.startsWith("http"))
					continue;
				URL u = new URL(url);
				if (set.get(u.getHost()) == null)
					set.put(u.getHost(), 0);
				set.put(u.getHost(), set.get(u.getHost()) + 1);
			}
			reader2.close();
			List<String> ll = new LinkedList<String>();
			for (Entry<String, Integer> entry : set.entrySet()) {
				ll.add(entry.getValue() + " " + entry.getKey().trim());
			}
			Collections.sort(ll, new ListComparator());
			MyWriter w = new MyWriter(file.getCanonicalPath() + "_count");
			for (String ls : ll)
				w.writeLine(ls);
			w.close();
		}
	}
}

class ListComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		int i1 = Integer.valueOf(o1.split(" ")[0]);
		int i2 = Integer.valueOf(o2.split(" ")[0]);
		if (i1 > i2)
			return -1;
		else if (i1 < i2)
			return 1;
		return 0;
	}

}
