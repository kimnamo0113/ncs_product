package ncs.exam;

import java.awt.EventQueue;

import ncs.exam.frame.FrameInputForm;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInputForm frame = new FrameInputForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
