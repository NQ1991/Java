import java.util.Scanner;
/*
����һƿ��ˮ4��8��Ǯ��ͬʱ�涨3����ƿ���Ի���1ƿ��ˮ����20����ƿ���Ի�
��7ƿ��ˮ������n(float)ԪǮ����ˮ�����ɺȶ���ƿ��ˮ��
*/
class getCokeUp{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		System.out.println("�м�ԪǮ��");
		float n=in.nextFloat();
		int coke=0;
		int bottle=((int)(100*n))/48;//���ﴦ��������ؼ�
		coke=bottle;//��Ǯ�򵽵�
		System.out.println("�����ƿ����"+bottle);
		while(bottle/20!=0){
			coke+=(bottle/20)*7;
			bottle=(bottle/20)*7+bottle%20;	
		}
		//System.out.println(bottle);
		//System.out.println(coke);
		while(bottle/3!=0){
			coke+=bottle/3;
			bottle=bottle/3+bottle%3;
		}
		System.out.println("ʣ��ƿ��       "+bottle);
		System.out.println("���ȿ���   "+coke);
	}
	
}