package 삼성sw역량테스트.햄버거다이어트;
import java.util.*;

public class Solution {

	public static ArrayList<Integer[]> list;
	public static int N;
	public static int limit;
	public static boolean[] check;
	public static int result;
	public static int count =0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int i=0;i<T;i++) {
			N = scan.nextInt();
			limit = scan.nextInt();
			result =0;
			list = new ArrayList<>();
			list.clear();

			for(int j=0;j<N;j++) {
				list.add(new Integer[] {scan.nextInt(),scan.nextInt()});
			}
			check = new boolean[N];
			
			search(0,0,0);
			System.out.println(String.format("#%d %d",i+1,result));
			System.out.println(count);
		}
	
	}
	public static void search(int score, int cal, int cnt) {
		count+=1;
		/*for(int i=0;i<list.size();i++) {
			System.out.print(check[i]);
			System.out.print(" ");
		}
		System.out.println(idx+1);*/
		//if(check[idx]) return;
		
		if(cnt == N) {
			if(cal < limit) {
				result = Math.max(result, score);
			}
			return;
		}
		search(score+list.get(cnt)[0],cal+list.get(cnt)[1],cnt+1);
		search(score,cal,cnt+1);
	}

}
