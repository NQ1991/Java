import java.util.Scanner;

/*
 * ��������ˮƿ���Ի�һƿ��ˮ
 * ��n������ˮƿ�������Ի�����ƿ��ˮ��
 */
class getCoke{
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		System.out.println("Ŀǰ�ж��ٿ�ƿ�ӣ�");
		int n=in.nextInt();
		int coke=0;
		int bottle=n;
		while(bottle/3!=0){
			coke+=bottle/3;
			bottle=bottle/3+bottle%3;
		}
		System.out.println("ʣ��ƿ��        "+bottle);
		System.out.println("���ȿ���    "+coke);
	}
}