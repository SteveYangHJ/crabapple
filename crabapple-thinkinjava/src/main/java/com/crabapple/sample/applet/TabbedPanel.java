package com.crabapple.sample.applet;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

// 创建'页签式的对话框'---注意其形式
public class TabbedPanel extends JApplet {

	private static final long serialVersionUID = -2953843658231418098L;
	private String[] flavors = { "Chocolate", "Strawberry",
			"Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge",
			"Rum Raisin", "Praline Cream", "Mud Pie" };
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);

	// 初始化
	public void init() {
		for (int i = 0; i < this.flavors.length; i++) {
			tabs.add(this.flavors[i], new JButton("Tabbed Pane" + i));
		}
		// 注册监听器类
		tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected:" + tabs.getSelectedIndex());
				String input = JOptionPane.showInputDialog("你输入内容:");
				JOptionPane.showMessageDialog(null, "你的输入是:" + input);
			}
		});
		Container con = this.getContentPane();
		con.add(BorderLayout.SOUTH, txt);
		con.add(tabs);
	}

	public static void main(String[] args) {
		Console.run(new TabbedPanel(), 400, 300);
	}
}
