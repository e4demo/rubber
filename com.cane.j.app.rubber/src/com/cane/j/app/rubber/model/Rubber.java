package com.cane.j.app.rubber.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Rubber used as the main business object
 * 
 * @author cane
 *
 */
public class Rubber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4401244629028416780L;

	private static int ID = 100_000;

	private static final int DEFAULT_COUNT = 1;

	private static final Float getPrice(Float width, Float height,
			Profile profile) {
		return new Float(2 * (width + height) * profile.getCost()
				+ profile.getMarkup());
	}

	private int id = 0;
	private Date date = null;
	private Float width = 0.0f;
	private Float height = 0.0f;
	private Profile profile;
	private int count = 1;
	private Float price;

	/**
	 * default constructor
	 * 
	 * @param width
	 * @param height
	 * @param profile
	 */
	public Rubber(Float width, Float height, Profile profile) {
		this(width, height, profile, ++ID, new Date(), DEFAULT_COUNT, getPrice(
				width, height, profile));
	}

	/**
	 * Full constructor
	 * 
	 * @param width
	 * @param height
	 * @param profile
	 * @param id
	 * @param date
	 * @param count
	 * @param price
	 */
	public Rubber(Float width, Float height, Profile profile, int id,
			Date date, int count, Float price) {
		this.id = id;
		this.date = date;
		this.width = width;
		this.height = height;
		this.profile = profile;
		this.count = count;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public static void setID(int id) {
		ID = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the width
	 */
	public Float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Float width) {
		this.width = width;

		// update price
		if (this.profile != null && this.height > 0.1F)
			this.price = getPrice(this.width, this.height, this.profile);
	}

	/**
	 * @return the height
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Float height) {
		this.height = height;

		// update price
		if (this.profile != null && this.width > 0.1F)
			this.price = getPrice(this.width, this.height, this.profile);
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;

		// update price
		if (this.height > 0.1F && this.width > 0.1F)
			this.price = getPrice(this.width, this.height, this.profile);
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

}
