package com.cane.j.app.rubber.list;

import com.cane.j.app.rubber.model.Rubber;

public interface IRubberListViewer {

	/**
	 * Update the view to reflect the fact that a rubber was added to the rubber
	 * list
	 * 
	 * @param task
	 */
	public void addLoop(Rubber loop);

	/**
	 * Update the view to reflect the fact that a rubber was removed from the
	 * rubber list
	 * 
	 * @param task
	 */
	public void removeLoop(Rubber loop);

	/**
	 * Update the view to reflect the fact that one of the rubbers was modified
	 * 
	 * @param task
	 */
	public void updateLoop(Rubber loop);

}
