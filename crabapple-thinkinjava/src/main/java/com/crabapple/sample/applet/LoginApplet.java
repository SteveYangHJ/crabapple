package com.crabapple.sample.applet;

//JFrame.setResizable(false);
//<applet code=LoginFrm width=200 height=100></applet>
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginApplet {
	public static void main(String args[]) {
		LoginFrame frame = new LoginFrame();
	}
}

class LoginFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -479784838553603468L;
	JLabel nameLabel = new JLabel("UserName：");
	JLabel pwdLabel = new JLabel("Password：");
	JTextField name = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	JButton butnSure = new JButton("Confirm");
	JButton butnCancel = new JButton("Cancel");

	public LoginFrame() {
		super("Login");
		setBounds(500, 200, 280, 220);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		nameLabel.setBounds(45, 20, 100, 25);
		add(nameLabel);
		add(name);
		name.setBounds(105, 20, 110, 25);
		add(pwdLabel);
		pwdLabel.setBounds(45, 60, 100, 25);
		add(password);
		password.setBounds(105, 60, 110, 25);
		add(butnSure);
		butnSure.setBounds(45, 100, 80, 25);
		add(butnCancel);
		butnCancel.setBounds(135, 100, 80, 25);
		butnSure.addActionListener(this);
		butnCancel.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butnSure) {
			System.out.println("UserName:" + name.getText());
			System.out.println("Password:" + name.getText());
			if ("admin".equals(name.getText().trim())
					&& "123".equals(password.getText().trim())) {
				this.dispose();
				new MainFrame("MainFrame", name.getText().trim(), password.getText()
						.trim());
			} else {
				JOptionPane.showMessageDialog(this, "WARNING: User isn't exist!");
			}
		} else if (e.getSource() == butnCancel) {
			System.exit(1);
		}
	}

	class MainFrame extends JFrame {
		private static final long serialVersionUID = 2151838650419624973L;
		private JLabel info;

		public MainFrame(String s, String name, String password) {
			super(s);
			setBounds(400, 200, 500, 400);
			setLayout(new FlowLayout());
			info = new JLabel("Login Successfully! UserName:" + name + ", Password:" + password);
			add(info);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			validate();
		}
	}
}