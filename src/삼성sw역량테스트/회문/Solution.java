package 삼성sw역량테스트.회문;
import java.util.*;

public class Solution {
	
	public static String[] data = new String[8];
	public static int leng;
	public static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = 2; //10
		for(int i=0;i<T;i++) {
			leng = scan.nextInt();
			scan.nextLine();//�ι��� �����
			result = 0;
			for(int j=0;j<8;j++) {
				data[j] = scan.nextLine();
			}
			
			for(int m=0;m<8;m++) {
				//System.out.println(data[m]);
				for(int n=0;n<8;n++) {
					char[] chs = new char[leng];
					searchR(m,n,0,chs);
					searchD(m,n,0,chs);
				}
			}
			System.out.println(String.format("#%d %d",i+1,result));
		}
	}
	
	public static void searchR(int m, int n, int cnt, char[] chs) {
		if(cnt==leng) {
			//ȸ������ �˻�
			int mid = cnt/2;
			if(cnt%2==1) {
				for(int i=1;i<=mid;i++) {
					if(chs[mid-i] != chs[mid+i]) {
						return;
					}
				}
			}
			if(cnt%2==0) {
				for(int i=0;i<mid;i++) {
					if(chs[mid+i] != chs[mid-(i+1)]) {
						return;
					}
				}
			}
			/*for(int i=0;i<cnt;i++) {
				System.out.print(chs[i]);
			}
			System.out.println("");*/
			result++;
			return;
		}
		if(m<0||n<0||m>=8||n>=8) return;
		chs[cnt] = data[m].charAt(n);
		searchR(m,n+1,cnt+1,chs);
	}
	
	public static void searchD(int m, int n, int cnt, char[] chs) {
		if(cnt==leng) {
			//ȸ������ �˻�
			int mid = cnt/2;
			if(cnt%2==1) {
				for(int i=1;i<=mid;i++) {
					if(chs[mid-i] != chs[mid+i]) {
						return;
					}
				}
			}
			if(cnt%2==0) {
				for(int i=0;i<mid;i++) {
					if(chs[mid+i] != chs[mid-(i+1)]) {
						return;
					}
				}
			}
			/*for(int i=0;i<cnt;i++) {
				System.out.print(chs[i]);
			}
			System.out.println("");*/
			result++;
			return;
		}
		if(m<0||n<0||m>=8||n>=8) return;
		chs[cnt] = data[m].charAt(n);
		searchD(m+1,n,cnt+1,chs);
	}

}
