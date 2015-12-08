package com.cane.j.app.rubber.provider;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import com.cane.j.app.rubber.list.IRubberList;
import com.cane.j.app.rubber.list.IRubberListViewer;
import com.cane.j.app.rubber.model.Rubber;

public class RubberContentProvider implements IStructuredContentProvider,
		IRubberListViewer {

	private IRubberList list = null;
	private TableViewer tableViewer = null;

	public RubberContentProvider(TableViewer tableViewer, IRubberList list) {
		this.tableViewer = tableViewer;
		this.list = list;
	}

	@Override
	public void dispose() {
		list.removeChangeListener(this);
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null)
			((IRubberList) newInput).addChangeListener(this);

		if (oldInput != null)
			((IRubberList) oldInput).removeChangeListener(this);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return list.getList().toArray();
	}

	@Override
	public void addLoop(Rubber loop) {
		tableViewer.add(loop);
	}

	@Override
	public void removeLoop(Rubber loop) {
		tableViewer.remove(loop);
	}

	@Override
	public void updateLoop(Rubber loop) {
		tableViewer.update(loop, null);
	}

}
