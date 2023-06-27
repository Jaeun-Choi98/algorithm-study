package 삼성sw역량테스트.간단한369게임;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		StringBuilder stringBuilder = new StringBuilder();
		boolean flag;
		for(int i=1;i<=N;i++) {
			int buf = i;
			flag = false;
			while(buf!=0) {
				if(buf%10==3||buf%10==6||buf%10==9) {
					flag = true;
					stringBuilder.append("-");
				}
				buf/=10;
			}
			
			if(!flag) {
				stringBuilder.append(i);
			}
			
			stringBuilder.append(" ");
		}
		System.out.println(stringBuilder);
	}

}
