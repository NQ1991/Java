import java.io.IOException;

//����һ�£��رյ���windows��ĳ������
//java��dosϵͳ
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