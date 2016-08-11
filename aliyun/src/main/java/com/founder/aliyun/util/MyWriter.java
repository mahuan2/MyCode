package com.founder.aliyun.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyWriter {
	private BufferedWriter writer;

	public MyWriter(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		writer = new BufferedWriter(new FileWriter(file));
	}

	public void writeLine(String line) throws IOException {
		writer.write(line + "\n");
	}

	public void close() throws IOException {
		writer.flush();
		writer.close();
	}
}
