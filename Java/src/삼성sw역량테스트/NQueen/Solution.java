package 삼성sw역량테스트.NQueen;

import java.util.*;

class MapAndRow{
	int[][] map;
	int row;
	public MapAndRow(int[][] map, int row) {
		this.map = map;
		this.row = row;
	}
	public int[][] getMap(){
		return map;
	}
	public int getRow() {
		return row;
	}
}

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		Stack<MapAndRow> stack = new Stack<>();
		
		for(int i=0;i<T;i++) {
			int n = scan.nextInt();
			//boolean[][] check = new boolean[n][n];
			int res = 0;
			stack.push(new MapAndRow(new int[n][n],0));
			while(!stack.isEmpty()) {
				MapAndRow mar = stack.pop();
				int row = mar.getRow();
				int[][] map = mar.getMap();
				/*for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						System.out.print(map[j][k]);
					}
					System.out.println("");
				}
				System.out.println("");*/
				if(row==n) {
					res++;
					continue;
				}
				for(int j=0;j<n;j++) {
					boolean flag = false;
					
					for(int k=0;k<n;k++) {
						//����
						if(map[k][j] == 1) {
							flag = true;
							break;
						}
						//����
						if(map[row][k]==1){
							flag = true;
							break;
						}
					}
					if(flag) continue;
					
					//�밢��
					int[] dx= {1,1,-1,-1};
					int[] dy= {1,-1,-1,1};
					for(int k=0;k<4;k++) {
						int x=row;
						int y=j;
						while(x>=0&&y>=0&&x<n&&y<n) {
							if(map[x][y] == 1) {
								flag = true;
								break;
							}
							x += dx[k];
							y += dy[k];
						}
						if(flag) break;
							
					}
					if(flag) continue;
					
					map[row][j] = 1;
					int[][] nMap = new int[n][n];
					for(int q=0;q<n;q++) {
						for(int r=0;r<n;r++) nMap[q][r] = map[q][r];
					}
					stack.push(new MapAndRow(nMap, row+1));
					map[row][j] = 0;
				}
			}
			System.out.println(String.format("#%d %d", i+1,res));
		}
	}

}
