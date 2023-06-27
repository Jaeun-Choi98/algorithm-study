package 다익스트라.파티_백준;
import java.util.*;

public class Main {

	public static ArrayList<Integer[]>[] grap;
	public static int[][] d;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		int X = scan.nextInt();
		grap = new ArrayList[N];
		d = new int[N][N];
		for(int i=0;i<N;i++) {
			grap[i] = new ArrayList<Integer[]>();
			for(int j=0;j<N;j++) {
				d[i][j] = Integer.MAX_VALUE;
				if(i==j) d[i][j] = 0;
			}
		}
		for(int i=0;i<M;i++) {
			int x = scan.nextInt()-1;
			int y = scan.nextInt()-1;
			int val = scan.nextInt();
			grap[x].add(new Integer[] {y,val});
		}
		
		//���ͽ�Ʈ�� �˰��� (�켱���� ť�� �̿���)
		for(int i=0;i<N;i++) {
			//0��° �迭: �Ÿ�
			PriorityQueue<Integer[]> pq = new PriorityQueue<>((p1,p2)->p1[0]-p2[0]);
			
			pq.add(new Integer[] {0,i});
			while(!pq.isEmpty()) {
				Integer[] cur = pq.poll();
				int idx = cur[1];
				int dist = cur[0];
				if(d[i][idx] < dist) continue; //��ü �� ���� �ǳʶڴ�. ex) 1���� 5������ �Ÿ��� 10�̾������� 1->3->5�� ���� 8�� ��ü�Ǿ�����
											  // 						10�̾��� Integer[]�� �ǳʶ�.
				for(int j=0;j<grap[idx].size();j++) {
					int nextIdx = grap[idx].get(j)[0];
					if(d[i][nextIdx] > dist + grap[idx].get(j)[1]) {
						d[i][nextIdx] = dist + grap[idx].get(j)[1];
						pq.add(new Integer[] {d[i][nextIdx],nextIdx});
					}
				}
				
			}
		}
		int result = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			int sum = d[X-1][i] + d[i][X-1];
			result = Math.max(sum,result);
		}
		System.out.println(result);
		
	}

}
