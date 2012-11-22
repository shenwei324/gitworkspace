package com.alan.shenw.jterminal.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Test extends JFrame implements ActionListener {

	private static final int WIDTH = 300;
	private static final int HEIGHT = 150;
	
	private JButton login;
	private JButton cancel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private void initComponents() {
		setTitle("Student Landing");
		login = new JButton("登录");
		cancel = new JButton("取消");
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		textField = new JTextField();
		passwordField = new JPasswordField();
		
		setBounds(getX(), getY(), 300, 350);
		setBackground(getBackground().BLACK);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(WIDTH, HEIGHT);
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;
		setLocation(x, y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		login.setText("登录");
		login.setActionCommand("login");
		login.addActionListener(this);
		
		cancel.setText("取消");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		
		label1.setText("username");
		label2.setText("password");
		label3.setText("information manager system");
		label3.setText("********");
		label3.setText("********");

		getParent().add(textField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
