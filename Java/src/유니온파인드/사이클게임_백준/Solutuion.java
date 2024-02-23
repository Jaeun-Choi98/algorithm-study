package 유니온파인드.사이클게임_백준;
import java.util.*;

public class Solutuion {
	public static int[] unionFindArr;

	public static int search(int x) {
		if(unionFindArr[x] == x) {
			return unionFindArr[x];
		}
		return search(unionFindArr[x]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		unionFindArr = new int[n];
		int count =0;
		boolean flag = false;
		for(int i=0;i<n;i++) unionFindArr[i] = i;
		for(int i=0;i<m;i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			count += 1;
			
			a = search(a);
			b = search(b);
			if(a==b) {
				flag = true;
				break;
			}
			
			if(a<b) {
				unionFindArr[b] = a;
			}else {
				unionFindArr[a] = b;
			}
		}
		if(flag) System.out.println(count);
		else System.out.println(0);
	}

}
