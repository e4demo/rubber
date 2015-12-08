/**
 * 
 */
package com.cane.j.app.rubber.list;

import java.util.List;

import com.cane.j.app.rubber.model.Rubber;

/**
 * @author cane
 *
 */
public interface IRubberList extends IRubberListViewer {

	public List<Rubber> getList();

	/**
	 * @param loop
	 */
	// public void addLoop(Rubber loop);
	// public void removeLoop(Rubber loop);
	// public void LoopChanged(Rubber loop);

	/**
	 * @param viewer
	 */
	public void removeChangeListener(IRubberListViewer viewer);

	public void addChangeListener(IRubberListViewer viewer);
}
