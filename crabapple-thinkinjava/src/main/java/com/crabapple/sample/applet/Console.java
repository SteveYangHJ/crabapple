package com.crabapple.sample.applet;

import javax.swing.*;
import java.awt.*;

// 定义工具类
public class Console {

	// 从任意对对象中抽取类名
	// create a title string from the class name
	private static String getTitle(Object obj) {
		// 使用RTTI机制
		String title = obj.getClass().toString();

		// remove the word "class"及空格
		if (title.indexOf("class") != -1) {
			title = title.substring(6);
		}
		return title;
	}

	// 重载方法,使之可用于JFrame,JApplet,JPanel
	public static void run(JFrame frame, int width, int height) {
		// To close the application,若无此方法,则程序无法关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置大小
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false); // 窗口大小不可变

		// 设置frame为可见
		// 必须调用此方法
		frame.setVisible(true);
		frame.validate();// 刷新
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame(getTitle(applet));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setSize(width,height);
		frame.setBounds(500, 500, width, height);
		// 有问题;----Why仍然可变
		frame.setResizable(false); // 窗口大小不可变
		frame.getContentPane().add(applet);

		// 需调用此方法
		applet.init();
		applet.start();
		frame.setVisible(true);
		frame.validate();// 刷新
	}

	public static void run(JPanel panel, int width, int height) {
		JFrame frame = new JFrame(getTitle(panel));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false); // 窗口大小不可变

		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.validate();// 刷新
	}

	public static void run(JComponent com, int width, int height) {
		JFrame frame = new JFrame(getTitle(com));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setSize(width,height);
		frame.setBounds(500, 500, width, height);
		// 对JComponent组件无问题?
		frame.setResizable(false); // 窗口大小不可变
		frame.getContentPane().add(com);

		// 需调用此方法
		frame.setVisible(true);
		frame.validate();// 刷新
	}
}// The end of class Console
