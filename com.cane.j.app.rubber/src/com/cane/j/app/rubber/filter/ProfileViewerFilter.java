package com.cane.j.app.rubber.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.cane.j.app.rubber.model.Profile;
import com.cane.j.app.rubber.model.Rubber;

public class ProfileViewerFilter extends ViewerFilter {

	private Profile profileFilter;

	public ProfileViewerFilter() {
		// super();
	}

	public ProfileViewerFilter(Profile p) {
		profileFilter = p;
	}

	public void setFilter(Profile p) {
		profileFilter = p;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		Rubber loop = (Rubber) element;
		if (profileFilter == null) {
			// without filter
			return true;
		} else if (profileFilter != null
				&& loop.getProfile().equals(profileFilter)) {
			// show only one profile
			return true;
		}

		return false;
	}

}
