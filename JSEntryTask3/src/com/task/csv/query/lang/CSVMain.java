package com.task.csv.query.lang;

import java.io.File;

public class CSVMain {
	
	public static void main(String[] args) {
		new CSVQueryLangProcessor(new File("files/csv-example")).start();
	}
}
