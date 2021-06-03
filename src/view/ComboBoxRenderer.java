package view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import misc.IDescriptor;

public class ComboBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		value = ((IDescriptor) value).getName();
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
}