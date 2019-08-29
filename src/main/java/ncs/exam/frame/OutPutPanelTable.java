package ncs.exam.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ncs.exam.dto.Sale;
import java.awt.GridLayout;

public class OutPutPanelTable extends JFrame {
	private JPanel contentPane;
	
	private DefaultTableModel model;
	private final String[] columns = {"","","","","","","","","",""};
	
	protected JTable table;
	protected List<Sale> itemList;
	private JTable table_1;
	
	public OutPutPanelTable() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 80));
		panel.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일선택
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(100, 30));
		panel_1.add(scrollPane_1);
		
		model=new DefaultTableModel(null,columns);
		table_1 = new JTable(model);
		scrollPane_1.add(table_1);
		scrollPane_1.setViewportView(table_1);
		
		
	}
	
	protected void setSumColumn(Sale sale) {
		DecimalFormat df=new DecimalFormat("###,###");
		columns[0]=sale.getNo()+"";
		columns[4]=sale.getSaleCnt()+"";
		columns[5]=df.format(sale.getSaleDetail().getSupplyPrice())+"";
		columns[6]=df.format(sale.getSaleDetail().getAddTax())+"";
		columns[7]=df.format(sale.getSaleDetail().getSalePrice())+"";
		columns[9]=df.format(sale.getSaleDetail().getMarginPrice())+"";
		
		model=new DefaultTableModel(null,columns);
		
		table_1.setModel(model);
	}
		protected void setItemList(List<Sale> itemList) {
		this.itemList = itemList;
	}
	
	protected String[] getColumnNames() {
		return new String[] { 
				"순위", 
				"제품코드", 
				"제품명",
				"제품단가",
				"판매수량",
				"공급가액",
				"부가세액",
				"판매금액",
				"마진율",
				"마진액"
		};
	}
	protected void reloadData() {
		MyTableModel model = new MyTableModel(getRows(), getColumnNames());
		table.setModel(model);
//		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2,3,4,5,6,7,8,9);
		tableSetWidth(100, 100, 100,100,100,100,100,100,100,100);
	};


	protected Object[][] getRows() {
		Object[][] rows=null;
		if (itemList == null) {
			rows = new Object[][] {};
		} else {
			rows = new Object[itemList.size()][];
			for (int i = 0; i < itemList.size(); i++) {
				rows[i] = toArray(i);
			}
		}
		return rows;

	}
	
	protected Object[] toArray(int i) {
		return itemList.get(i).toArray();
	}

	// 테이블 셀 내용의 정렬
	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	// 테이블 셀의 폭 설정
	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
}
