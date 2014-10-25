/*
 * ��client����
 * Server���ɽ���
 * client֮�䷢��Ϣ
 * ��ʾ��Ϣ���ݼ�����
 * ��ǿ---2014-10-25
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
			System.out.println("�˿����ڱ�ʹ�ã���ر���Ӧ����");
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
	 * ÿ�������������һ���߳�������
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
			while (connected) {//���������ǲ�ͬ���߳���vector��ͬ��������ǰ���client�������Ӻ����client
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			dos.writeUTF("Client"+clientNumber+"("+dateFormat.format(new Date()) +"):"+str);
		}
	}
}
