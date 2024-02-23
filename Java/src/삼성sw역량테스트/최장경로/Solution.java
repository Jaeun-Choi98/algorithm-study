package 삼성sw역량테스트.최장경로;
import java.util.*;

public class Solution {
	public static int result;
	public static boolean[] check;
	public static ArrayList<Integer>[] graph;
	public static int N,M;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int i=0;i<T;i++) {
			N = scan.nextInt();
			M = scan.nextInt();
			check = new boolean[N+1];
			graph = new ArrayList[N]; 
			result = 0;
			for(int j=0;j<N;j++) {
				graph[j] = new ArrayList<>();
			}
			for(int j=0;j<M;j++) {
				int a = scan.nextInt();
				int b = scan.nextInt();
				//������׷���
				graph[a-1].add(b);
				graph[b-1].add(a);
			}
			
			for(int j=0;j<N;j++) {
				check[j+1] = true;
				search(j,1,1);
				check[j+1] = false;
			}
			System.out.println(String.format("#%d %d", i+1, result));
		}
	}
	public static void search(int cur, int cnt, int leng) {
		result = Math.max(result, leng);
		if(cnt == N) return;
		for(int i=0;i<graph[cur].size();i++) {
			int a = graph[cur].get(i);	
			if(check[a]) continue;
			check[a] = true;
			search(a-1,cnt+1,leng+1);
			check[a] = false;
		}
	}

}
