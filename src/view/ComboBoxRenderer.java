package view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import misc.IDescriptor;

/**
 * ComboBoxRenderer class inherits {@link DefaultListCellRenderer}. Used to
 * decently display items in combo box.
 * 
 */
public class ComboBoxRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -2404553337902860555L;

	/**
	 * Return a component that has been configured to display the specified value.
	 * That component's paint method is then called to "render" the cell. If it is
	 * necessary to compute the dimensions of a list because the list cells do not
	 * have a fixed size, this method is called to generate a component on which
	 * getPreferredSize can be invoked
	 * 
	 * @param list         The JList we're painting
	 * @param value        The value returned by list.getModel().getElementAt(index)
	 * @param index        The cells index
	 * @param isSelected   True if the specified cell was selected
	 * @param cellHasFocus True if the specified cell has the focus
	 * 
	 * @return A component whose paint() method will render the specified value
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		value = ((IDescriptor) value).getName();
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
}
