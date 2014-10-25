import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * �޽��棬֧�ֶ�client�ķ����
 * 
 */
class TalkServer{
	public TalkServer() throws IOException{
		ServerSocket serverSocket=null;
		boolean listening=true;
		int clientNumber=0;//�ͻ�����ţ�������
		//��4444�˿ڳ��Խ���ServerSocketʵ��
		try{
			serverSocket=new ServerSocket(4444);
		}catch(IOException e){
			System.err.println("Couldn't connect the port 4444");
			System.exit(1);
		}
		//���ϼ����Ƿ���client��Ҫ����
		while(listening){
			Socket clientSocket=null;
			MultiTalkServerThread multiThread=null;
			//���Թ۲��ڴ��Ƿ�client��Ҫ����
			try{
				clientSocket=serverSocket.accept();
				clientNumber++;
				multiThread=new MultiTalkServerThread(clientSocket,clientNumber);
				multiThread.start();
				
			} catch(IOException e){
				System.err.println("Client "+clientNumber+" accept failed.");
	            //System.exit(1);
			}	
			//System.out.println("Client"+clientNumber+"���ӳɹ�");
		}	
	}
	public static void main(String args[]){
		try{
			new TalkServer();
		}catch(IOException e){
			 e.printStackTrace(); 
		}
		
	}
}
class MultiTalkServerThread extends Thread{
	private Socket clientSocket=null;
	private int clientNumber;
	public MultiTalkServerThread(Socket clientSocket,int clientNumber){
		this.clientSocket=clientSocket;
		this.clientNumber=clientNumber;
	}
	public void run(){//����������
		//������/�����
		PrintWriter toClient=null;
		BufferedReader fromClient = null;
		String clientInput = null;
			try {
			fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			toClient = new PrintWriter(clientSocket.getOutputStream(), true);
			clientInput=fromClient.readLine();
			}catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("From Client"+clientNumber+"'s Info:"+clientInput);
			try {
				fromClient.close();
				clientSocket.close();
				toClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
}