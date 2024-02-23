package 삼성sw역량테스트.Flatten;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = 10;
		for(int i=0;i<T;i++) {
			int dump = scan.nextInt();
			int[] arr = new int[100];
			for(int j=0;j<100;j++) {
				arr[j] = scan.nextInt();
			}
			
			int max,maxIdx,min,minIdx;
			max =0;
			maxIdx = 0;
			min =101;
			minIdx = 0;
			while(dump>1) {
				max =0;
				maxIdx = 0;
				min =101;
				minIdx = 0;
				for(int j=0;j<100;j++) {
					if(arr[j] >max) {
						max = arr[j];
						maxIdx = j;
					}
					if(arr[j]<min) {
						min = arr[j];
						minIdx = j;
					}
					
				}
				if(max > min) {
					arr[maxIdx] -= 1;
					arr[minIdx] += 1;
				}
				dump-=1;
			}
			for(int j=0;j<100;j++) {
				if(arr[j] >max) {
					max = arr[j];
					maxIdx = j;
				}
				if(arr[j]<min) {
					min = arr[j];
					minIdx = j;
				}
				
			}
			System.out.println(String.format("#%d %d", i+1, max - min));
			
		}
		
	}

}
