import java.util.Scanner;

/*
 * 三个空汽水瓶可以换一瓶汽水
 * 有n个空汽水瓶，最多可以换多少瓶汽水喝
 */
class getCoke{
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		System.out.println("目前有多少空瓶子：");
		int n=in.nextInt();
		int coke=0;
		int bottle=n;
		while(bottle/3!=0){
			coke+=bottle/3;
			bottle=bottle/3+bottle%3;
		}
		System.out.println("剩余瓶子        "+bottle);
		System.out.println("最多喝可乐    "+coke);
	}
}