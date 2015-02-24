package com.task.csv.query.lang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVQueryHandler {
	private CSVContent content;

	public CSVQueryHandler(final CSVContent content) {
		this.content = content;
	}

	public String handleSelectAllColumns(final String statement) {
		return selectColumns(statement, -1);
	}

	public String handleSelectColumnsWithLimit(final String statement) {
		final int limit = Integer.parseInt(statement.substring(statement.lastIndexOf(" ")).trim());
		return selectColumns(statement, limit);
	}

	
	public String handleSumIntsInColumn(final String statement) {
		final String columnName = statement.substring(statement.indexOf(" "));
		List<String> colValues = content.getColumnValues(columnName, -1);
		int sum = 0;
		for (String value : colValues) {
			try {
				sum += Integer.parseInt(value);
			} catch (NumberFormatException e) {
				// column value is not an integer
			}
		}
		return Integer.toString(sum);
	}

	
	public String handleShowAllColumnNames() {
		StringBuilder result = new StringBuilder();
		List<String> columnNames = content.getColumnNames();
		for (String name : columnNames) {
			result.append(name);
			if (columnNames.indexOf(name) < columnNames.size() - 1) {
				result.append(", ");
			}
		}
		return result.toString();
	}

	public String handleGetAllRowsWithMatchingCell(
			final String statement) {
		final String matchStatement = ".*" + statement.substring(
				statement.indexOf("\"") + 1, statement.lastIndexOf("\"")) + ".*";
		List<List<String>> matchingRows = new LinkedList<List<String>>();

		List<List<String>> allRows = content.getAllRows();
		for (List<String> row : allRows) {
			for (String value : row) {
				if (value.matches(matchStatement)) {
					matchingRows.add(row);
					break;
				}
			}
		}
		return new QueryResultVisualizationUtil().getRowsAsString(matchingRows);
	}

	private String selectColumns(final String statement, final int limit) {
		int limitStringIndex =  statement.indexOf(" LIMIT");
		int lastColumnNameIndex = limitStringIndex >= 0 ? limitStringIndex: statement.length();
		List<String> columnNames = Arrays.asList(statement.substring(
				statement.indexOf(" "), lastColumnNameIndex).split(
				CSVContent.ITEM_SPLIT_DELIMITER));
		Map<String, List<String>> columnsInfo = new HashMap<String, List<String>>();
		for (String name : columnNames) {
			columnsInfo.put(name, content.getColumnValues(name, limit));
		}
		return new QueryResultVisualizationUtil().getColumnsAsString(columnsInfo);
	}
}
