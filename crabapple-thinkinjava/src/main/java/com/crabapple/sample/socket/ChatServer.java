package com.crabapple.sample.socket;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.log4j.Logger;

/**
 *
 */
public class ChatServer {
	private Logger logger = Logger.getLogger(getClass());
	List<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new ChatServer().start();
	}

	public void start() {
		boolean started = false;
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e) {
			logger.error(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				clients.add(c);
				logger.info("a client connected!");
				new Thread(c).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create a new client for each request<br>
	 */
	private class Client implements Runnable {
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		boolean connected = false;

		public Client(Socket s) {
			try {
				this.socket = s;
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				connected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (connected) {
				try {
					Iterator<Client> it = clients.iterator();
					String msg = dis.readUTF();
					while (it.hasNext()) {
						Client c = it.next();
						c.send(msg);
					}
				} catch (EOFException eof) {
					connected = false;
					// 需要在序列中将退出的client去除，
					// 迭代过程中
					// 不能使用Collection本身的remove方法，
					// 会抛出java.util.ConcurrentModificationException
					// 使用iterator的remove方法
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void send(String str) throws IOException {
			dos.writeUTF(str);
		}
	}
}
