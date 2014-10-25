/*
 * ��client����
 * Server���ɽ���
 * client֮�䷢��Ϣ
 * ��ʾ��Ϣ���ݼ�����
 * ��ǿ---2014-10-25
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TalkClient extends Frame {
	private static final long serialVersionUID = 5798410085795768914L;
	public TextField tf = new TextField();
	public TextArea ta = new TextArea();

	DataOutputStream dos = null;
	DataInputStream dis = null;

	public Socket socket = null;

	boolean connected = false;

	Thread receiver = new Thread(new ReceiverThread());

	public static void main(String[] args) {
		new TalkClient().launchFrame();
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
				System.out.println("�ͻ��˶Ͽ�����!");
				System.out.println("�ͻ��˶Ͽ�����!");
				System.out.println("�ͻ��˶Ͽ�����!");
				connected = false;
			} catch (EOFException e) {
				System.out.println("�ͻ��˶Ͽ�����!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
