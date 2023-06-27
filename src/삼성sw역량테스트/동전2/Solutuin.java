package 삼성sw역량테스트.동전2;
import java.util.*;

public class Solutuin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int[] moneyList = new int[n];
		int[] dp = new int[k+1];
		int res = 0;
		for(int i=0;i<n;i++) {
			moneyList[i] = scan.nextInt();
		}
		
		for(int i=1;i<k+1;i++) {
			dp[i] = 100001;
			for(int j=0;j<n;j++) {
				if(i-moneyList[j] == 0) {
					dp[i] = 1;
					continue;
				}
				if(i-moneyList[j]>0 && dp[i-moneyList[j]] != 100001) {
					dp[i] = Math.min(dp[i], dp[i-moneyList[j]]+1);
				}
			}
			//System.out.println(dp[i]);
		}
		/*for(int i=1;i<k+1;i++) {
			System.out.println(dp[k]);
		}*/
		
		
		if(dp[k]==100001) res=-1;
		else res = dp[k];
		System.out.println(res);
		//����Ž������ ã��(DFS�̿�)
		/*for(int i=0;i<n;i++) {
			moneyList[i] = scan.nextInt();
		}
		int result=10001;
		Stack<Integer[]> stack = new Stack<Integer[]>();
		for(int i=0;i<n;i++) {
			stack.push(new Integer[] {moneyList[i],1});
			while(!stack.isEmpty()) {
				Integer[] sum = stack.pop();
				if(sum[0]>k) continue;
				if(sum[0]==k) {
					if(sum[1]<result) result = sum[1];
					continue;
				}
				
				for(int j=0;j<n;j++) {
					stack.push(new Integer[] {moneyList[i]+sum[0],sum[1]+1});
				}
			}
		}
		System.out.println(result);*/
	}

}
