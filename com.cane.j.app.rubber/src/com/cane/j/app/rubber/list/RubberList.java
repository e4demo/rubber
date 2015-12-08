package com.cane.j.app.rubber.list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.cane.j.app.rubber.model.Profile;
import com.cane.j.app.rubber.model.Rubber;

public class RubberList extends AbstractRubberList {

	private static final String path = "C:\\list.txt";
	private final File file;

	private static final String NL = System.getProperty("line.separator");
	private static final String SEP = "; ";

	public RubberList() {
		file = new File(path);
		loadData();
	}

	/*
	 * load data
	 */
	private void loadData() {
		if (file.exists() && file.canRead()) {

			try (FileReader fileReader = new FileReader(file);
					BufferedReader input = new BufferedReader(fileReader)) {

				String strLine = "";
				String[] tok = null;

				while ((strLine = input.readLine()) != null) {
					// Copy the content into the array
					tok = strLine.split(SEP);

					// Rubber(Float width, Float height, Profile profile, int
					// id, Date date, int count, Float price) {
					Rubber loop = new Rubber(Float.valueOf(tok[2]),
							Float.valueOf(tok[3]), Profile.valueOf(tok[4]),
							Integer.valueOf(tok[0]).intValue(), new Date(
									Long.valueOf(tok[1])), Integer.valueOf(
									tok[5]).intValue(), Float.valueOf(tok[6]));

					// set the Id conter
					Rubber.setID(Integer.valueOf(tok[0]).intValue());

					list.add(loop);
				}

			} catch (Exception e) {
				System.err.println(e);
			}

		}

	};

	/**
	 * Add a new loop to the collection of loops
	 */
	@Override
	public void addLoop(Rubber loop) {

		try (FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(fileWritter)) {

			out.write(getLine(loop));

		} catch (IOException e) {
			System.err.println(e);
		}

		// make other stuff
		super.addLoop(loop);
	}

	private String getLine(Rubber loop) {
		StringBuilder result = new StringBuilder();
		result.append(loop.getId()).append(SEP);
		result.append(String.valueOf(loop.getDate().getTime())).append(SEP);
		result.append(loop.getWidth().toString()).append(SEP);
		result.append(loop.getHeight().toString()).append(SEP);
		result.append(loop.getProfile().name()).append(SEP);
		result.append(loop.getCount()).append(SEP);
		result.append(loop.getPrice().toString()).append(SEP);
		result.append(NL);
		return result.toString();
	}

}
