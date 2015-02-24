package com.task.csv.query.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {
    private File csvFile;

    public CSVFileReader(final File csvFile) {
        this.csvFile = csvFile;
    }

    public List<String> getCSVContent() throws FileNotFoundException {
        List<String> allLines = new LinkedList<String>();
        Scanner csvFileScanner = new Scanner(csvFile);
        while (csvFileScanner.hasNextLine()) {
            allLines.add(csvFileScanner.nextLine());
        }
        csvFileScanner.close();
        return allLines;
    }
}
