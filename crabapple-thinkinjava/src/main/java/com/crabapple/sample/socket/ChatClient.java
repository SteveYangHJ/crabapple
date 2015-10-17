package com.crabapple.sample.socket;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;

import org.apache.log4j.Logger;

public class ChatClient extends Frame {
	private static final long serialVersionUID = 5798410085795768914L;
	private Logger logger = Logger.getLogger(getClass());
	public TextField tf = new TextField();
	public TextArea ta = new TextArea();

	DataOutputStream dos = null;
	DataInputStream dis = null;
	public Socket socket = null;
	boolean connected = false;

	Thread receiver = new Thread(new ReceiverThread());

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		this.setLocation(250, 100);
		this.setSize(500, 600);
		this.add(tf, BorderLayout.SOUTH);
		this.add(ta, BorderLayout.NORTH);
		this.pack();
		this.addWindowListener(new WindowMonitor());
		tf.addKeyListener(new KeyMonitor());
		connect();
		receiver.start();
		this.setVisible(true);
	}

	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			connected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class WindowMonitor extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			disconnect();
			System.exit(0);
		}
	}

	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (KeyEvent.VK_ENTER == keyCode) {
				try {
					dos.writeUTF(tf.getText().trim());
					dos.flush();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				tf.setText("");
			}
		}
	}

	private class ReceiverThread implements Runnable {
		public void run() {
			try {
				while (connected) {
					String str = dis.readUTF();
					ta.setText(ta.getText() + str + '\n');
				}
			} catch (SocketException e) {
				logger.error(e.getMessage());
				connected = false;
			} catch (EOFException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}