package 다익스트라.특정한최단경로_백준;
import java.util.*;

public class Main {

	public static int[] d1,dv1,dv2;
	public static ArrayList<Integer[]>[] grap;
	public static boolean[] check;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int v = scan.nextInt();
		int e = scan.nextInt();
		grap = new ArrayList[v+1];
		d1 = new int[v+1];
		dv1 = new int[v+1];
		dv2 = new int[v+1];
		check = new boolean[v+1];
		for(int i=1;i<v+1;i++) {
			grap[i] = new ArrayList<Integer[]>();
			d1[i] = Integer.MAX_VALUE;
			dv1[i] = Integer.MAX_VALUE;
			dv2[i] = Integer.MAX_VALUE;
		}
		for(int i=0;i<e;i++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			int val = scan.nextInt();
			grap[start].add(new Integer[] {end,val});
			grap[end].add(new Integer[] {start,val}); //������ �׷���
		}
		
		int v1,v2;
		v1 = scan.nextInt();
		v2 = scan.nextInt();
		dijstra(1,d1);
		dijstra(v1,dv1);
		dijstra(v2,dv2);
		int sv1 = d1[v1];
		int sv2 = d1[v2];
		int v1v2 = dv1[v2];
		int v1v = dv1[v];
		int v2v = dv2[v];
		int result = Integer.MAX_VALUE;
		if(sv1 == Integer.MAX_VALUE || v1v2 == Integer.MAX_VALUE || sv2 == Integer.MAX_VALUE
				|| v2v == Integer.MAX_VALUE || v1v == Integer.MAX_VALUE) System.out.println(-1);
		else{
			result = Math.min(result, sv1 + v1v2 + v2v);
			result = Math.min(result, sv2 + v1v2 + v1v);
			System.out.println(result);
		}
		
		/*for(int i=0;i<v+1;i++) {
			System.out.print(d1[i]);
			System.out.print(" ");
		}
		System.out.println("");
		
		for(int i=0;i<v+1;i++) {
			System.out.print(dv1[i]);
			System.out.print(" ");
		}
		System.out.println("");
		
		for(int i=0;i<v+1;i++) {
			System.out.print(dv2[i]);
			System.out.print(" ");
		}
		System.out.println("");*/
	}
	

	public static void dijstra(int start, int[] d) {
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((p1,p2)->p1[1]-p2[1]);
		d[start] = 0;
		pq.add(new Integer[] {start,d[start]});
		while(!pq.isEmpty()) {
			Integer[] curPair = pq.poll();
			int cur = curPair[0];
			int dis = curPair[1];
			
			if(dis>d[cur]) continue; //���ο� �Ÿ��� ������Ʈ �Ǿ��ٸ� �ǳʶٱ� ex)1->5 �� ���� �Ÿ��� 10�̾����� 1->3->5�� ���� �Ÿ��� 5�� ������Ʈ
									 // �Ǿ��ٸ� ������ �ִ� {5,10}�� �ǳʶڴ�.
			for(int i=0;i<grap[cur].size();i++) {
				int nextIdx = grap[cur].get(i)[0];
				int nextDis = dis + grap[cur].get(i)[1];
				if(d[nextIdx] > nextDis) {
					d[nextIdx] = nextDis;
					pq.add(new Integer[] {nextIdx,d[nextIdx]});
				}
			}
		}
	}

}
