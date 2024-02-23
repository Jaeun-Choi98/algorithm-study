package 삼성sw역량테스트.백만장자프로젝트1859;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int[][] data = new int[T][];
		
		
		for(int i=0;i<T;i++) {
			int leng = scan.nextInt();
			data[i]= new int[leng];
			for(int j=0;j<leng;j++) {
				data[i][j] = scan.nextInt();
			}
		}
		
		/*for(int i=0;i<T;i++) {
			for(int j=0;j<data[i].length;j++) {
				System.out.print(data[i][j]);
			}
			System.out.println("");
		}*/
		for(int i=0;i<T;i++) {
			long res = 0;
			for(int j=0;j<data[i].length;j++) {
				int maxBuf = data[i][j];
				for(int k=j+1;k<data[i].length;k++) {
					if(maxBuf<data[i][k]) {
						res += data[i][k] - maxBuf;
						maxBuf = data[i][k];
					}
				}
			}
			System.out.println(String.format("#%d %d", i+1,res));
		}
		
	}

}
