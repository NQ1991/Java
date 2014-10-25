import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 无界面，支持多client的服务端
 * 
 */
class TalkServer{
	public TalkServer() throws IOException{
		ServerSocket serverSocket=null;
		boolean listening=true;
		int clientNumber=0;//客户端序号（数量）
		//在4444端口尝试建立ServerSocket实例
		try{
			serverSocket=new ServerSocket(4444);
		}catch(IOException e){
			System.err.println("Couldn't connect the port 4444");
			System.exit(1);
		}
		//不断监听是否有client需要连接
		while(listening){
			Socket clientSocket=null;
			MultiTalkServerThread multiThread=null;
			//尝试观察在此是否client想要连入
			try{
				clientSocket=serverSocket.accept();
				clientNumber++;
				multiThread=new MultiTalkServerThread(clientSocket,clientNumber);
				multiThread.start();
				
			} catch(IOException e){
				System.err.println("Client "+clientNumber+" accept failed.");
	            //System.exit(1);
			}	
			//System.out.println("Client"+clientNumber+"连接成功");
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
	public void run(){//操作数据流
		//打开输入/输出流
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