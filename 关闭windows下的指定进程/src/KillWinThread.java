import java.io.IOException;

//运行一下，关闭电脑windows下某个进程
//java调dos系统
class KillWinThread{
	public static void main(String args[]){
		String  command="taskkill /f /im javaw.exe";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}