package ncs.exam.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncs.exam.dao.ProductMapper;
import ncs.exam.dao.ProductMapperImpl;
import ncs.exam.dao.SaleMapper;
import ncs.exam.dao.SaleMapperImpl;
import ncs.exam.dto.Product;
import ncs.exam.dto.Sale;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrameInputForm extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfPrice;
	private JTextField tfCount;
	private JTextField tfMargin;
	private JTextField tfName;
	private JButton btnInput;
	private JButton btnPrint1;
	private JButton btnPrint2;
	SaleMapper saleDao = new SaleMapperImpl();
	ProductMapper proDao = new ProductMapperImpl();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public FrameInputForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblCode = new JLabel("\uC81C\uD488\uCF54\uB4DC :");
		panel_5.add(lblCode);
		
		tfCode = new JTextField();
		tfCode.addKeyListener(this);
		panel_5.add(tfCode);
		tfCode.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("\uC81C\uD488\uB2E8\uAC00 :");
		panel_6.add(lblNewLabel_1);
		
		tfPrice = new JTextField();
		panel_6.add(tfPrice);
		tfPrice.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("\uD310\uB9E4\uC218\uB7C9 :");
		panel_7.add(lblNewLabel_2);
		
		tfCount = new JTextField();
		panel_7.add(tfCount);
		tfCount.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JLabel lblNewLabel_3 = new JLabel("\uB9C8\uC9C4\uC728 :");
		panel_8.add(lblNewLabel_3);
		
		tfMargin = new JTextField();
		panel_8.add(tfMargin);
		tfMargin.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\uC81C\uD488\uBA85");
		panel_4.add(lblNewLabel);
		
		tfName = new JTextField();
		panel_4.add(tfName);
		tfName.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnInput = new JButton("\uC785\uB825");
		btnInput.addActionListener(this);
		panel_1.add(btnInput);
		
		btnPrint1 = new JButton("\uCD9C\uB8251");
		btnPrint1.addActionListener(this);
		panel_1.add(btnPrint1);
		
		btnPrint2 = new JButton("\uCD9C\uB8252");
		btnPrint2.addActionListener(this);
		panel_1.add(btnPrint2);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPrint2) {
			actionPerformedBtnPrint2(e);
		}
		if (e.getSource() == btnPrint1) {
			actionPerformedBtnPrint1(e);
		}
		if (e.getSource() == btnInput) {
			actionPerformedBtnInput(e);
		}
	}
	protected void actionPerformedBtnInput(ActionEvent e) {
		
		
		saleDao.insertSale(getSaleTf());
		
	}
	protected void actionPerformedBtnPrint1(ActionEvent e) {
		FrameOutputSaleForm frame = new FrameOutputSaleForm();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnPrint2(ActionEvent e) {
		FrameOutputMarginForm frame = new FrameOutputMarginForm();
		frame.setVisible(true);
	}
	
	public Sale getSaleTf() {
		Sale sale = new Sale();
		sale.setProductCode(tfCode.getText());
		sale.setPrice(Integer.parseInt(tfPrice.getText()));
		sale.setSaleCnt(Integer.parseInt(tfCount.getText()));
		sale.setMarginrate(Integer.parseInt(tfMargin.getText()));
		return sale;
	}
	public void clearTf() {
		
	}
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == tfCode) {
			keyPressedTfCode(e);
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyPressedTfCode(KeyEvent e) {
		
	}
}
