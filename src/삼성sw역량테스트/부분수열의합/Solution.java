package 삼성sw역량테스트.부분수열의합;
import java.util.*;

public class Solution {

	public static int K;
	public static int N;
	public static int[] data;
	public static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int i=0;i<T;i++) {
			N = scan.nextInt();
			K = scan.nextInt();
			data = new int[N];
			result =0;
			for(int j=0;j<N;j++) {
				data[j] = scan.nextInt();
			}
			search(0,0);
			System.out.println(String.format("#%d %d", i+1,result));
		}
	}
	
	public static void search(int idx, int sum) {
		if(sum== K) {
			result++;
			return;
		}
		if(idx==N) return;
		if(sum>K) return;
		
		search(idx+1,sum+data[idx]);
		search(idx+1,sum);
	}
}
