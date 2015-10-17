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

public class Login {
	public static void main(String args[]) {
		LoginFrm frame = new LoginFrm();
	}
}

class LoginFrm extends JFrame implements ActionListener {
	JLabel nameLabel = new JLabel("用户名：");
	JLabel pwdLabel = new JLabel("密码：");
	JTextField name = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	JButton butnSure = new JButton("确定");
	JButton butnCancel = new JButton("取消");

	public LoginFrm() {
		super("登陆");
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
		validate();// 刷新
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butnSure) {
			System.out.println("用户名：" + name.getText());
			System.out.println("密码：" + name.getText());
			if ("admin".equals(name.getText().trim())
					&& "123".equals(password.getText().trim())) {
				this.dispose();
				new MainFrm("用户界面", name.getText().trim(), password.getText()
						.trim());
			} else {
				JOptionPane.showMessageDialog(this, "用户不存在");
			}
		} else if (e.getSource() == butnCancel) {
			System.exit(1);
		}
	}

	class MainFrm extends JFrame {
		private JLabel info;

		public MainFrm(String s, String name, String password) {
			super(s);
			setBounds(400, 200, 500, 400);
			setLayout(new FlowLayout());
			info = new JLabel("登陆成功，用户名：" + name + "，密码：" + password);
			add(info);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			validate();
		}
	}
}