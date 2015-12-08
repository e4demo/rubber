package com.cane.j.app.rubber.filter;

import java.util.Date;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.cane.j.app.rubber.model.Rubber;

public class TimeViewerFilter extends ViewerFilter {

	private long firstTime;
	private long lastTime;

	public TimeViewerFilter() {
		// super();
	}

	public void setFilter(Long firstTime, Long lastTime) {
		this.firstTime = firstTime.longValue();
		this.lastTime = lastTime.longValue();
	}

	public void setFilter(Date firstTime, Date lastTime) {
		this.firstTime = firstTime.getTime();
		this.lastTime = lastTime.getTime();
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		Rubber loop = (Rubber) element;

		long time = loop.getDate().getTime();

		if (firstTime == 0 && lastTime == 0) {
			return true;
		}

		if ((time >= firstTime) && (time <= lastTime)) {
			return true;
		}

		return false;
	}

}
