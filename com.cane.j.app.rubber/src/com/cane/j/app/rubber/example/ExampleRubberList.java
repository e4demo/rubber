package com.cane.j.app.rubber.example;

import java.util.ArrayList;
import java.util.Random;

import com.cane.j.app.rubber.list.AbstractRubberList;
import com.cane.j.app.rubber.model.Profile;
import com.cane.j.app.rubber.model.Rubber;

/**
 * Class that plays the role of the domain model in the TableViewerExample In
 * real life, this class would access a persistent store of some kind.
 * 
 */

public class ExampleRubberList extends AbstractRubberList {

	private final int COUNT = 4;
	private ArrayList<Rubber> defaultList = new ArrayList<Rubber>(COUNT);

	/**
	 * Constructor
	 */
	public ExampleRubberList() {
		super();
		list = new ArrayList<Rubber>(COUNT);
		initData();
	}

	/*
	 * Initialize the table data.
	 */
	private void initData() {
		Rubber loop1 = new Rubber(0.30F, 0.40F, Profile.P1);
		Rubber loop2 = new Rubber(0.58F, 1.14F, Profile.CT);
		Rubber loop3 = new Rubber(0.58F, 1.53F, Profile.EA);
		Rubber loop4 = new Rubber(0.58F, 1.53F, Profile.E3);

		// fill the default list
		defaultList.add(loop1);
		defaultList.add(loop2);
		defaultList.add(loop3);
		defaultList.add(loop4);

		// fill the worked list
		list.addAll(defaultList);
	};

	/**
	 * Add a new loop to the collection of loops
	 */
	public void addLoop() {
		// get random loop from the default list
		Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(COUNT);
		Rubber loop = defaultList.get(id);

		// make other stuff
		super.addLoop(loop);
	}

}
