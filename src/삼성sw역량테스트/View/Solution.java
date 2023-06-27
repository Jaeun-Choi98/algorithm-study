package 삼성sw역량테스트.View;

import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int[] dx = {-1,-2,1,2};
		for(int i=0;i<T;i++) {
			int res =0;
			int x = scan.nextInt();
			int[] arr = new int[x];
			for(int j=0;j<x;j++) {
				arr[j] = scan.nextInt();
			}
			for(int j=2;j<x-2;j++) {
				int max = 0;
				for(int k = 0 ;k<4;k++) {
					if(arr[j + dx[k]] > max) {
						max = arr[j+dx[k]];
					}
				}
				if(arr[j] - max > 0) res += arr[j] - max;
			}
			System.out.println(String.format("#%d %d", i+1,res));
		}
	}

}
