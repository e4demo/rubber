package com.cane.j.app.rubber.provider;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.cane.j.app.rubber.model.Rubber;

public class RubverColumnLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	private DateFormat dateFormat;
	private NumberFormat numberFormat;

	public RubverColumnLabelProvider() {
		dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
		numberFormat = NumberFormat.getCurrencyInstance();
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";

		if (element instanceof Rubber) {
			Rubber loop = (Rubber) element;

			switch (columnIndex) {
			case 0:
				result = String.valueOf(loop.getId());
				break;
			case 1:
				result = dateFormat.format(loop.getDate());
				break;
			case 2:
				result = String.valueOf(loop.getWidth());
				break;
			case 3:
				result = String.valueOf(loop.getHeight());
				break;
			case 4:
				result = loop.getProfile().name();
				break;
			case 5:
				result = String.valueOf(loop.getCount());
				break;
			case 6:
				result = numberFormat.format(loop.getPrice());
				break;
			default:
				break;
			}
		}
		return result;
	}

}
