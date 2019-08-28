package ncs.exam.frame;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{
	public MyTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
