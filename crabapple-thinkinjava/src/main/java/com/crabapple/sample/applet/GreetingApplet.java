package com.crabapple.sample.applet;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * 在*.java文件中直接加入以下<applet/>, 然后就可以直接在命令行上运行applet程序
 * command: Appletviewer *.java   ---
 * html: <applet code="GreetingApplet" width=200 height=100></applet>
 * @author SteveHJ
 */
@SuppressWarnings("serial")
public class GreetingApplet extends JApplet implements ActionListener {
	private JTextField txtInputName;
	private JLabel lblOutput;
	private JButton btnGreet;

	@Override
	public void init() {
		txtInputName = new JTextField();
		lblOutput = new JLabel("Hello,");
		btnGreet = new JButton("greet");
		txtInputName.setSize(new Dimension(200, 10));
		lblOutput.setSize(new Dimension(200, 10));
		btnGreet.setSize(new Dimension(40, 10));
		// 注册事件
		btnGreet.addActionListener(this);

		// 布局
		// this.add(new JButton("以下为布局:"));
		getContentPane().setLayout(new GridLayout(3, 1));
		getContentPane().add(lblOutput);
		getContentPane().add(txtInputName);
		getContentPane().add(btnGreet);
		getContentPane().setSize(new Dimension(200, 100));
		getContentPane().setVisible(true);
	}

	// 点击按钮事件的实现逻辑在此方法中实现
	public void actionPerformed(ActionEvent e) {
		// 判断是哪个按钮的事件
		if (e.paramString().equals("greet")) {
			lblOutput.setText("Hello," + txtInputName.getText());
		}
	}

	public static void main(String[] args) {
		JApplet app = new GreetingApplet();
		JFrame frame = new JFrame("app");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(app);
		frame.setSize(300, 200);

		app.init();
		app.start();
		// 必须调用此方法
		frame.setVisible(true);
	}
}
