package com.founder.aliyun.util;

public class Page {
	int cur = 0;
	int total = 0;

	public Page(int total) {
		this.total = total;
	}

	public String next() {
		if (cur < total)
			return "" + (++cur);
		else {
			return "" + total;
		}
	}

	public static void main(String[] args) {
		Page page = new Page(3);
		System.out.println(page.next());
		System.out.println(page.next());
		System.out.println(page.next());
		System.out.println(page.next());
	}
}
