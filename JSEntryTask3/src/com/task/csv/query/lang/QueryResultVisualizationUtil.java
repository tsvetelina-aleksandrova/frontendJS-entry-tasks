package com.task.csv.query.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QueryResultVisualizationUtil {
	private static final char COLUMN_DELIMITER = '|';
	private static final char ROW_DELIMITER = '-';
	
	
	public String getColumnsAsString(final Map<String, List<String>> columnsInfo){
		StringBuilder result = new StringBuilder();
		//array list
		List<String> columnNames = Arrays.asList(columnsInfo.keySet().toArray(new String[0]));
		Collections.reverse(columnNames);
		String rowDelimiter = createRowDelimiter(columnNames);
		result.append(getRowAsString(columnNames, rowDelimiter));
		result.append(rowDelimiter).append("\n");
		int rowsNumber = columnsInfo.get(columnNames.get(0)).size();
		
		List<List<String>> allRows = new LinkedList<List<String>>();
		for(int i = 0; i < rowsNumber; i++){
			List<String> row = new LinkedList<String>();
			for(String name : columnNames){
				row.add(columnsInfo.get(name).get(i));
			}
			allRows.add(row);
		}
		return result.append(getRowsAsString(allRows)).toString();
	}
	
	public String getRowsAsString(final List<List<String>> rowsList){
		StringBuilder result = new StringBuilder();
		Iterator<List<String>> iter = rowsList.iterator();
		
		while(iter.hasNext()){
			List<String> currentRow = iter.next();
			String rowDelimiter = createRowDelimiter(currentRow);
			result.append(getRowAsString(currentRow, rowDelimiter));
			if(!iter.hasNext()){
				result.append(rowDelimiter + "\n");
			}
		}
		return result.toString();
	}
	
	private String getRowAsString(final List<String> currentRow, final String rowDelimiter){
		StringBuilder result = new StringBuilder();
		result.append(rowDelimiter + "\n");
		for(String value : currentRow){
			result.append(COLUMN_DELIMITER + value);
		}
		return result.append(COLUMN_DELIMITER + "\n").toString();
	}
	
	private String createRowDelimiter(final List<String> row){
		StringBuilder delimiter = new StringBuilder(" ");
		for(String value : row){
			char result[] = new char[value.length()];
	        Arrays.fill(result, ROW_DELIMITER);
			delimiter.append(result).append(" ");
		}
		return delimiter.toString();
	}
}
