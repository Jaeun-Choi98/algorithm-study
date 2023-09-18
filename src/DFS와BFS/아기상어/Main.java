package DFS와BFS.아기상어;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n;
    static int[][] map;
    static int[][] check;
    static int size, curEat;
    static boolean flag;
    static int findX, findY,findDist;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int sx = -1;
        int sy = -1;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                }
            }
        }

        size = 2;
        curEat = 0;
        res = 0;
        while(true){
            check = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    check[i][j] = -1;
                }
            }

            flag = false;
            findX = Integer.MAX_VALUE;
            findY = Integer.MAX_VALUE;
            findDist = Integer.MAX_VALUE;

            //먹을 수 있는 조건에 맞는 물고기 1마리 찾기
            search(sx,sy);
            if(flag){
                res += findDist;
                curEat++;
                if(curEat==size){
                    size++;
                    curEat=0;
                }
                map[findX][findY] = 0;
                sx = findX;
                sy = findY;
            }else break;
        }
        bw.write(res + "\n");
        bw.flush();
        bw.close(); br.close();
    }

    public static void search(int x, int y){
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{x, y});
        check[x][y] = 0;
        while (!que.isEmpty()) {
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=n||check[nx][ny]!=-1||map[nx][ny]>size) continue;
                check[nx][ny] = check[buf[0]][buf[1]] + 1;
                if(map[nx][ny] !=0 && map[nx][ny] < size){
                    flag = true;
                    //거리가 더 짧은 물고기를 선택
                    if(findDist>check[nx][ny]){
                        findX = nx;
                        findY = ny;
                        findDist = check[nx][ny];
                    }//거리가 같다면 조건에 맞는 물고기 선택
                    else if(findDist == check[nx][ny]){
                        if(findX>nx){
                            findX = nx;
                            findY = ny;
                        }else if(findX == nx){
                            if(findY>ny){
                                findX = nx;
                                findY = ny;
                            }
                        }
                    }
                }
                que.add(new Integer[]{nx, ny});
            }
        }
    }
}
