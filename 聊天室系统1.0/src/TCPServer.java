import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * 服务器端，给出当前客户端的连接信息
 * 关闭/重启按键功能
 */
class TCPServer extends JFrame{
	private int HEIGHT=400;
	private int WIDTH=400;
	//private JPanel infoPanel,buttonPanel;
	private JTextArea INFO=new  JTextArea();
	private final String buttonStr[] = {"重启","关闭","清屏"};
	private JButton BUTTON[]=new JButton[buttonStr.length];
	public TCPServer(){
		int  i;
		JPanel infoPanel=new JPanel(new GridLayout(1,1));
		infoPanel.setBounds(20, 40, 250, 250);
		infoPanel.setBorder((Border) new TitledBorder(new EtchedBorder(), "infoPanel"));
		infoPanel.add(INFO);
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setBounds(10, 100, 20, 40);
		buttonPanel.setBorder((Border) new TitledBorder(new EtchedBorder(), "buttonPanel"));
		//for(i=0;i<buttonStr.length;i++){
			//BUTTON[i]=new JButton(buttonStr[i]);
			//buttonPanel.add(BUTTON[i]);
		//}
		BUTTON[1]=new JButton("hehe");
		buttonPanel.add(BUTTON[1]);
		
		Container con =getContentPane();
		con.add(infoPanel);
		con.add(buttonPanel);
		this.setTitle("服务器4444");
		this.setSize(WIDTH,HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//public void start(){}
	public static void main(String args[]){
		new TCPServer();
		
	}
	
}