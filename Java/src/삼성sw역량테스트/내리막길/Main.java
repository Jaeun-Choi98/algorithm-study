package 삼성sw역량테스트.내리막길;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan =new Scanner(System.in);
		int M =scan.nextInt();
		int N =scan.nextInt();
		
		int[][] map = new int[M][N];
		int[][] dp = new int[M][N];
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = scan.nextInt();
			}
		}
		
		dp[0][0] = 1;
		int[] dm = {0,0,1,-1};
		int[] dn = {1,-1,0,0};
		int res =0;
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[]{0,0});
		while(!stack.isEmpty()) {
			Integer[] buf = stack.pop();
			if(buf[0] == M-1 && buf[1] == N-1) {
				res += 1;
				continue;
			}
			for(int i=0;i<4;i++) {
				int nm = buf[0] + dm[i];
				int nn = buf[1] + dn[i];
				if(nm<0 || nn <0 || nm>= M || nn >= N) continue;
				if(map[buf[0]][buf[1]] > map[nm][nn]) {
					if(dp[nm][nn] !=0) {
						res += 1;
						continue;
					}
					dp[nm][nn] += 1;
					stack.push(new Integer[] {nm,nn});
				}
			}
			
		}
		
		/*for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(dp[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}*/
		System.out.println(res);
	}

}
