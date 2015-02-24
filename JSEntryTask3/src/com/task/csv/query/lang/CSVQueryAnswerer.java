package com.task.csv.query.lang;

public class CSVQueryAnswerer {
    public static final String SELECT_COLS = "^SELECT (\\S)+(, (\\S)+)*";
    public static final String SELECT_COLS_ROWLIMIT = SELECT_COLS + " LIMIT [0-9]+";
    public static final String SUM_INTS_IN_COLUMN = "^SUM (\\S)+";
    public static final String SHOW_ALL_COLUMN_NAMES = "^SHOW$";
    public static final String ROWS_WITH_COLUMN_MATCHING = "^FIND \".+\"";

    private CSVQueryHandler queryHandler;

    public CSVQueryAnswerer(final CSVContent content) {
        this.queryHandler = new CSVQueryHandler(content);
    }

    public String answerQuery(String statement){
        statement = statement.trim().replaceAll("\\s", " ");
        		
        if (statement.matches(SELECT_COLS)) {
            return queryHandler.handleSelectAllColumns(statement);
        } else if (statement.matches(SELECT_COLS_ROWLIMIT)) {
            return queryHandler.handleSelectColumnsWithLimit(statement);
        } else if (statement.matches(SUM_INTS_IN_COLUMN)) {
        	return queryHandler.handleSumIntsInColumn(statement);
        } else if (statement.matches(SHOW_ALL_COLUMN_NAMES)) {
        	return queryHandler.handleShowAllColumnNames();
        } else if (statement.matches(ROWS_WITH_COLUMN_MATCHING)) {
            return queryHandler.handleGetAllRowsWithMatchingCell(statement);
        } else {
            return"Query statement doesn't belong to our language.";
        }
    }
}