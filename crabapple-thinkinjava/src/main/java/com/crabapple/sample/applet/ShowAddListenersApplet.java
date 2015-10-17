package com.crabapple.sample.applet;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.regex.*;

/**
 * Display the "addXXXListener" method of any Swing class
 * <applet code=ShowAddListenersApplet width=200 height=100></applet>
 * @date 2011.3.23
 */
@SuppressWarnings("serial")
public class ShowAddListenersApplet extends JApplet {
	private JTextField txtName = new JTextField(25);
	private JTextArea txtResults = new JTextArea(40, 65);

	// 使用正则表达式进行匹配
	private static Pattern addListener = Pattern
			.compile("(add\\w+?Listener\\(.*?\\))");
	private static Pattern qualifier = Pattern.compile("\\w+\\.");

	// 定义内部监听器类,处理事件
	class EventNameListener implements ActionListener {
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			String nm = txtName.getText().trim();
			if (nm.length() == 0) {
				txtResults.setText("No match!");
				return;
			}
			Class klass;
			try {
				klass = Class.forName("javax.swing." + nm);

			} catch (ClassNotFoundException ex) {
				txtResults.setText("No match!");
				return;
			}
			Method[] methods = klass.getMethods();
			txtResults.setText("");
			for (int i = 0; i < methods.length; i++) {
				// 通过传递每个Method对象给Pattern.matcher()方法,创建Matcher对象
				Matcher matcher = addListener.matcher(methods[i].toString());

				if (matcher.find()) {
					// group(1)选择第一个匹配的"表达式组",这样得到的字符串仍然还有限定词
					// 需使用Pattern的对象qualifier
					txtResults.append(qualifier.matcher(matcher.group(1))
							.replaceAll("") + "\n");
				}
			}
		}
	}

	@Override
	public void init() {
		EventNameListener nameListener = new EventNameListener();
		txtName.addActionListener(nameListener);
		JPanel top = new JPanel();
		// Set layout
		top.setLayout(new GridLayout(2, 1));
		top.add(new JLabel("Swing class name(press ENTER)"));
		top.add(this.txtName);
		// top.setMinimumSize(new Dimension(200,10));
		Container con = this.getContentPane();
		// con.setLayout(new GridLayout(2,1));
		con.add(BorderLayout.NORTH, top);
		con.add(BorderLayout.CENTER, new JScrollPane(this.txtResults));

		// Initial data and test
		this.txtName.setText("JTextArea");
		nameListener.actionPerformed(new ActionEvent("", 0, ""));
	}

	// A main() for the application
	public static void main(String[] args) {
		Console.run(new ShowAddListenersApplet(), 500, 400);
	}
}
