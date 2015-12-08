package com.cane.j.app.rubber.part;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.cane.j.app.rubber.example.ExampleRubberList;
import com.cane.j.app.rubber.filter.ProfileViewerFilter;
import com.cane.j.app.rubber.filter.TimeViewerFilter;
import com.cane.j.app.rubber.list.IRubberList;
import com.cane.j.app.rubber.list.RubberList;
import com.cane.j.app.rubber.model.Profile;
import com.cane.j.app.rubber.model.Rubber;
import com.cane.j.app.rubber.provider.RubberContentProvider;
import com.cane.j.app.rubber.provider.RubverColumnLabelProvider;
import com.cane.j.app.rubber.view.RubberViewerDefinition;

public class RubberPart {

	// private Label label;

	private Table table;
	private TableViewer tableViewer;

	// TableViewer input
	private IRubberList list;

	private Text widthText;
	private Text heightText;

	private Button addButton;

	private Combo profilesCombo;
	private Profile profile = null;

	// filters
	private ProfileViewerFilter profileFilter;
	private TimeViewerFilter timeFilter;

	private Combo profileFilterCombo;
	private Button timeFilterCheckBox;
	private DateTime fromDate;
	private DateTime fromTime;
	private DateTime toDate;
	private DateTime toTime;
	private Date fromDateTime;
	private Date toDateTime;
	private Text priceFromText;
	private Text priceToText;

	@PostConstruct
	public void createComposite(final Composite parent) {

		createInputForm(parent);

		// input list
		createRuberList();

		fillCombo(profilesCombo, false);

		// Create the table
		createTable(parent);

		// FILTERS
		createFilters(parent);

		// Create and setup the TableViewer
		createTableViewer();
	}

	private void createRuberList() {
		list = new ExampleRubberList();
		//list = new RubberList();
	}

	/**
	 * Create the input form
	 */
	private void createInputForm(Composite parent) {
		parent.setLayout(new GridLayout(7, false));

		Label widthLabel = new Label(parent, SWT.NONE);
		widthLabel.setText("\u0448\u0438\u0440\u0438\u043D\u0430");
		new Label(parent, SWT.NONE);

		Label heightLabel = new Label(parent, SWT.NONE);
		heightLabel.setText("\u0432\u044B\u0441\u043E\u0442\u0430");
		new Label(parent, SWT.NONE);

		Label profileLabel = new Label(parent, SWT.NONE);
		profileLabel.setText("\u043F\u0440\u043E\u0444\u0438\u043B\u044C");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		heightText = new Text(parent, SWT.BORDER);
		GridData gd_heightText = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_heightText.widthHint = 80;
		heightText.setLayoutData(gd_heightText);

		Label heightUnitsLabel = new Label(parent, SWT.NONE);
		GridData gd_heightUnitsLabel = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_heightUnitsLabel.widthHint = 40;
		heightUnitsLabel.setLayoutData(gd_heightUnitsLabel);
		heightUnitsLabel.setText("\u043C");

		widthText = new Text(parent, SWT.BORDER);
		GridData gd_widthText = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_widthText.widthHint = 80;
		widthText.setLayoutData(gd_widthText);

		Label widthUnitsLabel = new Label(parent, SWT.NONE);
		GridData gd_widthUnitsLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_widthUnitsLabel.widthHint = 40;
		widthUnitsLabel.setLayoutData(gd_widthUnitsLabel);
		widthUnitsLabel.setText("\u043C");

		// ComboBox with list of Profiles
		profilesCombo = new Combo(parent, SWT.READ_ONLY);
		profilesCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (profilesCombo.getText() != null) {
					profile = Profile.valueOf(profilesCombo.getText());
				}
			}
		});
		GridData gd_profilesCombo = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_profilesCombo.widthHint = 80;
		profilesCombo.setLayoutData(gd_profilesCombo);

		// Add button
		addButton = new Button(parent, SWT.NONE);
		GridData gd_addButton = new GridData(SWT.LEFT, SWT.LEFT, true, false,
				1, 1);
		gd_addButton.widthHint = 60;
		addButton.setLayoutData(gd_addButton);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewLoop();
			}
		});
		new Label(parent, SWT.NONE);

		// separator
		Label sep = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		// gd_sep.widthHint = 442;
		sep.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));

		// result label
		Label resultLabel = new Label(parent, SWT.NONE);
		resultLabel.setText("text: ");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		// label = new Label(parent, SWT.NONE);
		// label.setText("Sample table");
	}

	/**
	 * put all profiles names into Combo list
	 */
	private void fillCombo(Combo c, boolean addEmpty) {
		if (addEmpty) {
			c.add("");
		}
		for (Profile p : Profile.values()) {
			c.add(p.name());
		}
	}

	/**
	 * Create the Table
	 */
	private void createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		table = new Table(parent, style);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 149;
		gridData.horizontalSpan = 7;
		gridData.grabExcessVerticalSpace = true;
		table.setLayoutData(gridData);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		createTableColumns();

		// Add listener to column so tasks are sorted by description when
		// clicked
		// column.addSelectionListener(new SelectionAdapter() {
		//
		// public void widgetSelected(SelectionEvent e) {
		// tableViewer.setSorter(new
		// ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
		// }
		// });
	}

	/**
	 * create UI filters elements
	 * 
	 * @param parent
	 */
	private void createFilters(Composite parent) {
		// FILTERS
		Composite filtersComposite = new Composite(parent, SWT.NONE);
		filtersComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 7, 1));

		// profile filter
		createProfileFilter(filtersComposite);

		// time filter
		createTimeFilter(filtersComposite);

		// size filter
		createSizeFilter(filtersComposite);

		// price filter
		createPriceFilter(filtersComposite);
	}

	/**
	 * @param parent
	 */
	private void createProfileFilter(Composite parent) {
		// profile filter group
		Group profileFilterGroup = new Group(parent, SWT.NONE);
		profileFilterGroup.setText("Profile filter");
		profileFilterGroup.setBounds(0, 0, 73, 90);

		// profile filter
		profileFilter = new ProfileViewerFilter();

		// profile filter combo
		profileFilterCombo = new Combo(profileFilterGroup, SWT.READ_ONLY);
		profileFilterCombo.setBounds(10, 21, 53, 21);
		profileFilterCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (profileFilterCombo.getText().length() > 0) {
					// set profile filter
					profileFilter.setFilter(Profile.valueOf(profileFilterCombo
							.getText()));
				} else {
					// remove profile filter
					profileFilter.setFilter(null);
				}
				tableViewer.refresh();
			}
		});

		fillCombo(profileFilterCombo, true);
	}

	/**
	 * @param parent
	 */
	private void createTimeFilter(Composite parent) {

		Group timeFilterGroup = new Group(parent, SWT.NONE);
		timeFilterGroup.setText("Time filter");
		timeFilterGroup.setBounds(79, 0, 222, 90);
		timeFilterGroup.setLayout(new GridLayout(4, false));

		// profile filter
		timeFilter = new TimeViewerFilter();

		timeFilterCheckBox = new Button(timeFilterGroup, SWT.CHECK);
		timeFilterCheckBox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (timeFilterCheckBox.getSelection()) {
					setEnabledTime(true);

					// TODO
					changeTimeFilter();
					tableViewer.addFilter(timeFilter);
				} else {
					setEnabledTime(false);
					tableViewer.removeFilter(timeFilter);
				}
				tableViewer.refresh();
			}
		});

		Label lblFrom = new Label(timeFilterGroup, SWT.NONE);
		lblFrom.setText("from");

		fromDate = new DateTime(timeFilterGroup, SWT.BORDER | SWT.DROP_DOWN);
		fromDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setFromDateTime();
				changeTimeFilter();
			}
		});
		fromDate.setEnabled(false);

		fromTime = new DateTime(timeFilterGroup, SWT.BORDER | SWT.TIME
				| SWT.SHORT);
		fromTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setFromDateTime();
				changeTimeFilter();
				tableViewer.refresh();
			}
		});
		fromTime.setEnabled(false);
		new Label(timeFilterGroup, SWT.NONE);

		setFromDateTime();

		Label lblTo = new Label(timeFilterGroup, SWT.NONE);
		lblTo.setText("to");

		toDate = new DateTime(timeFilterGroup, SWT.BORDER | SWT.DROP_DOWN);
		toDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setToDateTime();
				changeTimeFilter();
				tableViewer.refresh();
			}
		});
		toDate.setEnabled(false);

		toTime = new DateTime(timeFilterGroup, SWT.BORDER | SWT.TIME
				| SWT.SHORT);
		toTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setToDateTime();
				changeTimeFilter();
				tableViewer.refresh();
			}
		});
		toTime.setEnabled(false);

		setToDateTime();
	}

	/**
	 * 
	 * @param parent
	 */
	private void createSizeFilter(Composite parent) {

		Group grpSizeFilter = new Group(parent, SWT.NONE);
		grpSizeFilter.setText("size filter");
		grpSizeFilter.setBounds(307, 0, 102, 90);

		// TODO
	}

	/**
	 * 
	 * @param parent
	 */
	private void createPriceFilter(Composite parent) {

		Group grpPriceFilter = new Group(parent, SWT.NONE);
		grpPriceFilter.setText("price filter");
		grpPriceFilter.setBounds(415, 0, 119, 90);
		grpPriceFilter.setLayout(new GridLayout(3, false));

		// TODO
		Button priceFilterCheckBox = new Button(grpPriceFilter, SWT.CHECK);

		Label priceFromLabel = new Label(grpPriceFilter, SWT.NONE);
		priceFromLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		priceFromLabel.setText("from");

		priceFromText = new Text(grpPriceFilter, SWT.BORDER);
		priceFromText.setEnabled(false);
		priceFromText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(grpPriceFilter, SWT.NONE);

		Label lblTo_1 = new Label(grpPriceFilter, SWT.NONE);
		lblTo_1.setText("to");

		priceToText = new Text(grpPriceFilter, SWT.BORDER);
		priceToText.setEnabled(false);
		priceToText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
	}

	private void setEnabledTime(boolean bool) {
		if (fromDate != null) {
			fromDate.setEnabled(bool);
		}
		if (fromTime != null) {
			fromTime.setEnabled(bool);
		}
		if (toDate != null) {
			toDate.setEnabled(bool);
		}
		if (toTime != null) {
			toTime.setEnabled(bool);
		}
	}

	/**
	 * Create the Table columns
	 */
	private void createTableColumns() {
		TableColumn column;
		for (int c = 0; c < RubberViewerDefinition.NUMBER_COLUMNS; c++) {
			column = new TableColumn(table,
					RubberViewerDefinition.columnAligns[c], c);
			column.setText(RubberViewerDefinition.columnLabels[c]);
			column.setWidth(RubberViewerDefinition.columnSizes[c]);
		}
	}

	/**
	 * Create the TableViewer
	 */
	private void createTableViewer() {

		// tableViewer = new TableViewer(parent);
		tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		tableViewer.setColumnProperties(RubberViewerDefinition.columnNames);

		// Setup the TableViewer

		tableViewer.setContentProvider(new RubberContentProvider(tableViewer,
				list));
		tableViewer.setLabelProvider(new RubverColumnLabelProvider());

		// The input for the table viewer is the instance of ExampleTaskList
		tableViewer.setInput(list);

		// TODO
		// Set the default sorter for the viewer
		// tableViewer.setSorter(new
		// ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));

		// set Filters
		tableViewer.addFilter(profileFilter);
		// tableViewer.addFilter(timeFilter);
	}

	@Focus
	public void onFocus() {
		widthText.setFocus();
	}

	/*
	 * Close the window and dispose of resources
	 */
	public void close() {
		Shell shell = table.getShell();

		if (shell != null && !shell.isDisposed())
			shell.dispose();
	}

	private void setFromDateTime() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, fromDate.getYear());
		cal.set(Calendar.MONTH, fromDate.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, fromDate.getDay());

		cal.set(Calendar.HOUR_OF_DAY, fromTime.getHours());
		cal.set(Calendar.MINUTE, fromTime.getMinutes());

		fromDateTime = cal.getTime();
	}

	private void setToDateTime() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, toDate.getYear());
		cal.set(Calendar.MONTH, toDate.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, toDate.getDay());

		cal.set(Calendar.HOUR_OF_DAY, toTime.getHours());
		cal.set(Calendar.MINUTE, toTime.getMinutes());

		toDateTime = cal.getTime();
	}

	private void changeTimeFilter() {
		if (fromDateTime != null && toDateTime != null)
			timeFilter.setFilter(Long.valueOf(fromDateTime.getTime()),
					Long.valueOf(toDateTime.getTime()));
	}

	private void addNewLoop() {
		if (widthText.getText().length() > 0
				&& heightText.getText().length() > 0 && profile != null) {
			// TODO
			Float w = Float.valueOf(widthText.getText());
			Float h = Float.valueOf(heightText.getText());

			Rubber loop = new Rubber(w, h, profile);
			list.addLoop(loop);
		}
	}
}