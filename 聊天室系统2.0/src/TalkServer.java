/*
 * 多client界面
 * Server不可界面
 * client之间发消息
 * 显示消息内容及日期
 * 那强---2014-10-25
 * 
 * */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class TalkServer {

	Vector<Client> clients = new Vector<Client>();
	
	public static void main(String[] args) {
		new TalkServer().start();
	}

	public void start() {
		boolean listening = false;
		ServerSocket ss = null;
		int clientNumber=0;
		try {
			ss = new ServerSocket(8888);
			listening = true;
		} catch (BindException e) {
			System.out.println("端口正在被使用，请关闭相应程序！");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (listening) {
				Socket s = ss.accept();
				Client c = new Client(s,clientNumber);
				clients.add(c);
				System.out.println("Client  "+clientNumber+" connected!");
				new Thread(c).start();
				clientNumber++;
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
	 * 每个请求过来启用一个线程来处理
	 */
	private class Client implements Runnable {
		Socket socket = null;
		int clientNumber=0;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		boolean connected = false;

		Client(Socket s,int clientNumber) {
			try {
				this.socket = s;
				this.clientNumber=clientNumber;
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				connected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (connected) {//最大的问题是不同的线程中vector不同步，导致前面的client不能连接后面的client
				try {
					/*
					Iterator<Client> it = clients.iterator();
					String msg = dis.readUTF();
					while (it.hasNext()) {
						Client c = it.next();
						c.send(msg,clientNumber);
					}
					*/
					String msg = dis.readUTF();
					for(int i=0;i<clients.size();i++){
						clients.get(i).send(msg,clientNumber);
					}

				} catch (EOFException eof) {
					//heheh
					connected = false;
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void send(String str,int clientNumber) throws IOException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			dos.writeUTF("Client"+clientNumber+"("+dateFormat.format(new Date()) +"):"+str);
		}
	}
}
