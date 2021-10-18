package 시물레이션.감시;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int count =999999999;
    static int[][] arr;
    static int N,M;
    public static void main(String[] args) {
        List<Camera> listCamera = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); M = scanner.nextInt();
        arr = new int[N][M];
        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                arr[i][j] = scanner.nextInt();
                if(arr[i][j]!=0&&arr[i][j]!=6) listCamera.add(new Camera(i,j,arr[i][j]));
            }
        }

        run(listCamera,arr,0);
        System.out.println(count);
        /*Arrays.stream(arr).forEach(ints -> {
            Arrays.stream(ints).forEach(System.out::print);
            System.out.println();
        });*/
    }
    public static void run(List<Camera> listCamera, int[][] arr, int start){
        if(start == listCamera.size()) return;
        for(int i=start;i<listCamera.size();i++){
            int x = listCamera.get(i).x; int y = listCamera.get(i).y; int number = listCamera.get(i).number;
            if(number==1){
                int[] dx = {1,0,-1,0};
                int[] dy = {0,-1,0,1};
                for(int dir=0;dir<4;dir++){
                    int nx = x; int ny = y;
                    while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                            &&arr[nx + dx[dir]][ny + dy[dir]]<7){
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        arr[nx][ny] = 7;
                    }

                    run(listCamera,arr,start+1);
                    count = Math.min(count,countZero(arr));

                    //초기화
                    nx =x; ny = y;
                    while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                            &&arr[nx + dx[dir]][ny + dy[dir]]==7){
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        arr[nx][ny] = 0;
                    }
                }
            }
            else if(number==2){
                int dx[] = {1,0};
                int dy[] = {0,1};
                for(int dir=0;dir<2;dir++){
                    int nx = x; int ny = y;
                    while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                            &&arr[nx + dx[dir]][ny + dy[dir]]!=6&&arr[nx + dx[dir]][ny + dy[dir]]!=7){
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        arr[nx][ny] = 7;
                    }
                    while (nx + -dx[dir]>=0&&nx + -dx[dir]< N&&ny + -dy[dir]>=0&&ny + -dy[dir]<M
                            &&arr[nx + -dx[dir]][ny + -dy[dir]]!=6&&arr[nx + -dx[dir]][ny + -dy[dir]]!=7){
                        nx = nx + -dx[dir]; ny = ny + -dy[dir];
                        arr[nx][ny] = 7;
                    }
                    run(listCamera,arr,start+1);
                    count = Math.min(count,countZero(arr));

                    //초기화
                    nx =x; ny = y;
                    while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                            &&arr[nx + dx[dir]][ny + dy[dir]]==7){
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        arr[nx][ny] = 0;
                    }
                    while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                            &&arr[nx + dx[dir]][ny + dy[dir]]==7){
                        nx = nx + -dx[dir]; ny = ny + -dy[dir];
                        arr[nx][ny] = 0;
                    }
                }

            }
            else if(number==3){

            }
            else if(number==4){

            }
            else if(number==5){

            }

        }
    }
    public static int countZero(int[][] arr){
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==0) count++;
            }
        }
        return count;
    }
}

class Camera{
    public int x,y,number;
    public Camera(int x, int y, int number){
        this.x = x; this.y = y; this.number = number;
    }
}