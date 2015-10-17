package com.crabapple.sample.applet;


import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 *
 */
@SuppressWarnings("serial")
public class TrackEventApplet extends JApplet {
	// 用来存放表示事件类型的字符串,以及对JTextField的引用
	private HashMap eventTypeMap = new HashMap();
	// 事件字符串
	private String[] event = { "focusGained", "focusLost", "keyPressed",
			"keyReleased", "keyTyped", "mouseClicked", "mouseEntered",
			"mouseExited", "mousePressed", "mouseReleased", "mouseDragged",
			"mouseMoved" };
	private UserButton btnBlue = new UserButton(Color.BLUE, "test");
	private UserButton btnRed = new UserButton(Color.RED, "test2");

	// 定义用户自定义Button,内部类
	class UserButton extends JButton {
		// 构造函数
		public UserButton(Color color, String text) {
			super(text);
			this.setBackground(color);

			// 注册监听类(监听事件)
			this.addFocusListener(fl);
			this.addKeyListener(kl);
			this.addMouseListener(ml);
			this.addMouseMotionListener(mml);
		}

		//
		void report(String field, String message) {
			((JTextField) eventTypeMap.get(field)).setText(message);
		}

		//
		FocusListener fl = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				report("focusGained", e.paramString());
			}

			@Override
			public void focusLost(FocusEvent e) {
				report("focusLost", e.paramString());
			}

		};

		//
		KeyListener kl = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				report("keyPressed", e.paramString());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				report("keyReleased", e.paramString());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				report("keyTyped", e.paramString());
			}
		};
		//
		MouseListener ml = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				report("mouseClicked", e.paramString());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				report("mouseEntered", e.paramString());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				report("mouseExited", e.paramString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				report("mousePressed", e.paramString());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				report("mouseReleased", e.paramString());
			}
		};
		//
		MouseMotionListener mml = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				report("mouseDragged", e.paramString());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				report("mouseMoved", e.paramString());
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		Container con = getContentPane();
		con.setLayout(new GridLayout(event.length + 1, 2));
		for (int i = 0; i < event.length; i++) {
			JTextField txtMsg = new JTextField();
			txtMsg.setEditable(false);
			con.add(new JLabel(event[i], JLabel.RIGHT));
			con.add(txtMsg);
			eventTypeMap.put(event[i], txtMsg);
		}
		con.add(btnBlue);
		con.add(btnRed);
	}

	// A main() for the application
	public static void main(String[] args) {
		Console.run(new TrackEventApplet(), 700, 500);
	}

}// /:~
