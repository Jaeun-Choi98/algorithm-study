package 다익스트라.인간대포_백준;
import java.io.*;
import java.util.*;


public class Main {

	static ArrayList<Float[]>[] grap;
	static float[] val;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Float[] start = new Float[2];
		StringTokenizer s = new StringTokenizer(br.readLine());
		start[0] = Float.parseFloat(s.nextToken());
		start[1] = Float.parseFloat(s.nextToken());
		
		Float[] end = new Float[2];
		s = new StringTokenizer(br.readLine());
		end[0] = Float.parseFloat(s.nextToken());
		end[1] = Float.parseFloat(s.nextToken());
		
		int n = Integer.parseInt(br.readLine());
		grap = new ArrayList[n+2];
		val = new float[n+2];
		ArrayList<Float[]> cordinate = new ArrayList<Float[]>();
		for(int i=0;i<n+2;i++) {
			grap[i] = new ArrayList<Float[]>();
			val[i] = Integer.MAX_VALUE;
		}
		
		cordinate.add(start);
		for(int i=0;i<n;i++) {
			Float[] buf = new Float[2];
			s = new StringTokenizer(br.readLine());
			buf[0] = Float.parseFloat(s.nextToken());
			buf[1] = Float.parseFloat(s.nextToken());
			cordinate.add(buf);
		}
		cordinate.add(end);
		
		//�׷��� �����
		for(int i=1;i<n+2;i++) {
			float dis = (float) Math.sqrt(Math.pow(cordinate.get(0)[0]-cordinate.get(i)[0], 2)
					+ Math.pow(cordinate.get(0)[1]-cordinate.get(i)[1], 2));
			grap[0].add(new Float[] {(float)i, (float)(dis/5.0)});
		}
		for(int i=1;i<n+2;i++) {
			for(int j=0;j<n+2;j++) {
				float dis = (float) Math.sqrt(Math.pow(cordinate.get(i)[0]-cordinate.get(j)[0], 2)
						+ Math.pow(cordinate.get(i)[1]-cordinate.get(j)[1], 2));
				grap[i].add(new Float[] {(float)j,
						(float)Math.min(dis/5.0, Math.abs(dis-50)/5.0 + 2.0)});
			}
		}
		dijkstra(0);
		bw.write(val[n+1] + "\n");
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Float[]> pq = new PriorityQueue<>((p1,p2)->Float.compare(p1[1],p2[1]));
		val[start] = (float)0;
		pq.add(new Float[]{(float)start,(float)0});
		
		while(!pq.isEmpty()) {
			Float[] buf = pq.poll();
			int cur = (int)(float)(buf[0]);
			float dis = buf[1];
			if(dis>val[cur]) continue;
			for(int i=0;i<grap[cur].size();i++) {
				int nextIdx = (int)(float)grap[cur].get(i)[0];
				float nextDis = (float)(dis + grap[cur].get(i)[1]);
				if(val[nextIdx]>nextDis) {
					val[nextIdx] = nextDis;
					pq.add(new Float[] {(float)nextIdx,nextDis});
				}
			}
		}
	}
}
