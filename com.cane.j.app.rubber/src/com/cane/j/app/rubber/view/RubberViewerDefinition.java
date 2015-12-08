package com.cane.j.app.rubber.view;

import org.eclipse.swt.SWT;

public final class RubberViewerDefinition {

	// Set the table column property names
	public static final String ID_COLUMN = "id";
	public static final String DATE_COLUMN = "date";
	public static final String WIDTH_COLUMN = "width";
	public static final String HEIGHT_COLUMN = "height";
	public static final String TYPE_COLUMN = "type";
	public static final String COUNT_COLUMN = "count";
	public static final String PRICE_COLUMN = "price";

	// Set column names
	public static final String[] columnNames = new String[] { ID_COLUMN,
			DATE_COLUMN, WIDTH_COLUMN, HEIGHT_COLUMN, TYPE_COLUMN,
			COUNT_COLUMN, PRICE_COLUMN };

	// Set column labels
	public static String[] columnLabels = new String[] { "Id", "Time",
			"width, " + "м", "height, " + "м", "type", "count",
			"price, " + "руб" };

	// Set column sizes
	public static int[] columnSizes = new int[] { 80, 160, 60, 60, 40, 40, 110 };

	// Set column aligns
	public static int[] columnAligns = new int[] { SWT.LEFT, SWT.LEFT,
			SWT.LEFT, SWT.LEFT, SWT.CENTER, SWT.CENTER, SWT.LEFT };

	public static final int NUMBER_COLUMNS = columnNames.length;

}
