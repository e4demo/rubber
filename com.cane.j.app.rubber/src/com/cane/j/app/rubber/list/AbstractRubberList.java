/**
 * 
 */
package com.cane.j.app.rubber.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cane.j.app.rubber.model.Rubber;

/**
 * @author cane
 *
 */
public abstract class AbstractRubberList implements IRubberList {

	protected List<Rubber> list;
	protected Set<IRubberListViewer> changeListeners;

	/**
	 * 
	 */
	public AbstractRubberList() {
		list = new ArrayList<Rubber>();
		changeListeners = new HashSet<IRubberListViewer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cane.j.app.rubber.model.IRubberList#getList()
	 */
	@Override
	public List<Rubber> getList() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cane.j.app.rubber.model.IRubberList#addLoop()
	 */
	@Override
	public void addLoop(Rubber loop) {
		// put it into the end of list
		list.add(list.size(), loop);

		Iterator<IRubberListViewer> iterator = changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().addLoop(loop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cane.j.app.rubber.model.IRubberList#removeLoop(com.cane.j.app.rubber
	 * .model.Rubber)
	 */
	@Override
	public void removeLoop(Rubber loop) {
		list.remove(loop);

		Iterator<IRubberListViewer> iterator = changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().removeLoop(loop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cane.j.app.rubber.model.IRubberList#LoopChanged(com.cane.j.app.rubber
	 * .model.Rubber)
	 */
	@Override
	public void updateLoop(Rubber loop) {
		Iterator<IRubberListViewer> iterator = changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().updateLoop(loop);
	}

	/**
	 * @param viewer
	 */
	public void removeChangeListener(IRubberListViewer viewer) {
		changeListeners.remove(viewer);
	}

	/**
	 * @param viewer
	 */
	public void addChangeListener(IRubberListViewer viewer) {
		changeListeners.add(viewer);
	}

}
