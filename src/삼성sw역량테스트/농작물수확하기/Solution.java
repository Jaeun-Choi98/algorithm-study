package 삼성sw역량테스트.농작물수확하기;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int i=0;i<T;i++) {
			int n = scan.nextInt();
			scan.nextLine();
			int[][] arr = new int[n][n];
			int res = 0;
			for(int j=0;j<n;j++) {
				String buf = scan.nextLine();
				for(int k=0;k<n;k++) {
					arr[j][k] = buf.charAt(k) - '0';
					res += arr[j][k];
					//System.out.print(arr[j][k]);
				}
				//System.out.println("");
			}
			int row = 0;
			for(;row<n/2;row++) {
				for(int j=0;j<n/2 - row;j++) {
					res -= arr[row][j];
					res -= arr[n-row-1][j];
				}
				for(int j=n-1;j>n/2+row;j--) {
					res -= arr[row][j];
					res -= arr[n-row-1][j];
				}
				
			}
			System.out.println(String.format("#%d %d", i+1,res));
		}
	}

}
