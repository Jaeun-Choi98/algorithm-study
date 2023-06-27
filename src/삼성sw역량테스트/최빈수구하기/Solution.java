package 삼성sw역량테스트.최빈수구하기;
import java.util.*;

public class Solution {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int i=0;i<T;i++){
			scan.nextInt();
			int[] dp = new int[101];
			for(int j=0;j<1000;j++) {
				dp[scan.nextInt()] += 1;
			}
			int max = -1;
			int maxScore= -1;
			for(int j=100;j>=0;j--) {
				if(max < dp[j]) {
					max = dp[j];
					maxScore = j;
				}
				/*if(max == dp[j]) {
					if(maxScore<j) {
						max = dp[j];
						maxScore=j;
					}
				}*/
			}
			System.out.println(String.format("#%d %d", i+1,maxScore));
		}
		
		
	}

}
