package com.alan.shenw.jterminal.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TerminalConsole extends JFrame {

	private static final long serialVersionUID = 6133141589120799187L;

	private JTextField textField;
	private JTextArea textArea;

	public TerminalConsole() {
		textField = new JTextField();
		textArea = new JTextArea();

		initUI();
	}

	private void initUI() {
		Font font = new Font("Consolas", Font.PLAIN, 16);
		
		JTextField textField = new JTextField();
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.GREEN);
		textField.setFont(font);
		
		JTextArea txaDisplay = new JTextArea();
		txaDisplay.setBackground(Color.BLACK);
		txaDisplay.setForeground(Color.GREEN);
		txaDisplay.setFont(font);
		
		JScrollPane scroll = new JScrollPane(txaDisplay);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(400, 500));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		pack();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				TerminalConsole tc = new TerminalConsole();
				tc.setVisible(true);
			}
		});
	}
}
