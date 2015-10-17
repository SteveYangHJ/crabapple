package com.crabapple.sample.applet;

import javax.swing.*;
import java.awt.*;

/**
 * Utility
 * @author SteveHJ
 *
 */
public class Console {

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

	// Overload for JFrame,JApplet,JPanel
	public static void run(JFrame frame, int width, int height) {
		// To close the application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(new Dimension(width, height));
		frame.setResizable(false);

		// Make frame visible
		frame.setVisible(true);
		frame.validate();
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame(getTitle(applet));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setSize(width,height);
		frame.setBounds(500, 500, width, height);
		frame.setResizable(false);
		frame.getContentPane().add(applet);

		applet.init(); // need invoke this method
		applet.start();
		frame.setVisible(true);
		frame.validate();
	}

	public static void run(JPanel panel, int width, int height) {
		JFrame frame = new JFrame(getTitle(panel));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(width, height));
		frame.setResizable(false);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.validate();
	}

	public static void run(JComponent com, int width, int height) {
		JFrame frame = new JFrame(getTitle(com));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setSize(width,height);
		frame.setBounds(500, 500, width, height);
		frame.setResizable(false);
		frame.getContentPane().add(com);

		frame.setVisible(true);
		frame.validate();// 刷新
	}
}// The end of class Console
