import java.util.Scanner;
/*
定价一瓶汽水4角8分钱，同时规定3个空瓶可以换回1瓶汽水，或20个空瓶可以换
回7瓶汽水。今有n(float)元钱买汽水，最多可喝多少瓶汽水？
*/
class getCokeUp{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		System.out.println("有几元钱：");
		float n=in.nextFloat();
		int coke=0;
		int bottle=((int)(100*n))/48;//这里处理浮点数最关键
		coke=bottle;//用钱买到的
		System.out.println("最初空瓶子数"+bottle);
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
		System.out.println("剩余瓶子       "+bottle);
		System.out.println("最多喝可乐   "+coke);
	}
	
}