package com.task.csv.query.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVQueryLangProcessor {
	private CSVQueryAnswerer answerer;
	private static final Logger LOG = Logger
			.getLogger(CSVQueryLangProcessor.class.getName());

	public CSVQueryLangProcessor(final File csvFile) {
		createQueryAnswerer(csvFile);
	}

	public void start(){
		String userInput = null;
		@SuppressWarnings("resource")
		final Scanner inputScanner = new Scanner(System.in);
		do{
			System.out.println("Enter your CSV query (or q to exit):");
			userInput = inputScanner.nextLine();
			System.out.println(answerer.answerQuery(userInput));
		} while(userInput != null && !userInput.equalsIgnoreCase("q"));
	}

	private final void createQueryAnswerer(final File csvFile) {
		try {
			this.answerer = new CSVQueryAnswerer(new CSVContent(
					new CSVFileReader(csvFile).getCSVContent()));
		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "CSV file was not opened correctly", e);
		}
	}
}
