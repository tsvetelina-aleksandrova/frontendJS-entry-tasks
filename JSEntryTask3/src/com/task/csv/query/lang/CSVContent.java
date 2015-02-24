package com.task.csv.query.lang;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CSVContent {
    static final String ITEM_SPLIT_DELIMITER = ",";
    private List<String> lines;

    public CSVContent(final List<String> lines) {
        this.lines = lines;
    }

    public List<String> getColumnNames() {
        String headerLine = lines.get(0);
        return Arrays.asList(headerLine.split(ITEM_SPLIT_DELIMITER));
    }

    public List<String> getColumnValues(final String columnName, int limit) {
        List<String> columnValueLines = new LinkedList<String>();
        int columnIndex = getColumnNames().indexOf(columnName.trim());
        List<String> allValues = getAllValues();
        if (limit == -1 || limit > allValues.size()) {
            limit = allValues.size();
        }
        for (int i = 0; i < limit; i++) {
            columnValueLines.add(allValues.get(i).split(ITEM_SPLIT_DELIMITER)[columnIndex].trim());
        }
        return columnValueLines;
    }

    public List<List<String>> getAllRows() {
        List<List<String>> allRows = new LinkedList<List<String>>();
        for (String line : lines) {
            allRows.add(Arrays.asList(line.split(ITEM_SPLIT_DELIMITER)));
        }
        return allRows;
    }

    private List<String> getAllValues() {
        List<String> valueLines = new LinkedList<String>();
        for(int i = 1; i < lines.size(); i++){
        	valueLines.add(lines.get(i));
        }
        return valueLines;
    }
}
