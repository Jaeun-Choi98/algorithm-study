package 시물레이션.감시;

import java.util.*;

public class Main {
    static int count = 0;
    static int[][] arr,copyArr;
    static int N,M;
    public static void main(String[] args) {
        //카메라의 위치와 번호를 담을 객체
        List<Camera> listCamera = new ArrayList<>();
        //값을 초기화 해줄 객체
        Stack<Reset> resets = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); M = scanner.nextInt();
        arr = new int[N][M]; copyArr = new int[N][M];
        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                arr[i][j] = scanner.nextInt();
                if(arr[i][j]!=0&&arr[i][j]!=6) listCamera.add(new Camera(i,j,arr[i][j]));
                if(arr[i][j]==0) count++;
            }
        }
        run(listCamera,resets,0);
        System.out.println(count);
    }

    public static void run(List<Camera> listCamera, Stack<Reset> resets, int start){
        if(start == listCamera.size()) {
            /*Arrays.stream(arr).forEach(ints -> {
                Arrays.stream(ints).forEach(System.out::print);
                System.out.println();
            });
            System.out.println();*/
            return;
        }

        int x = listCamera.get(start).x; int y = listCamera.get(start).y; int number = listCamera.get(start).number;
        if(number==1){
            int[] dx = {1,0,-1,0};
            int[] dy = {0,-1,0,1};
            for(int dir=0;dir<4;dir++){
                int nx = x; int ny = y;
                while (nx + dx[dir]>=0&&nx + dx[dir]< N&&ny + dy[dir]>=0&&ny + dy[dir]<M
                        &&arr[nx + dx[dir]][ny + dy[dir]]!=6){
                    if(arr[nx + dx[dir]][ny + dy[dir]]!=0) {
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        continue;
                    }
                    nx = nx + dx[dir]; ny = ny + dy[dir];
                    arr[nx][ny] = 7;
                    //초기화 시킬 좌표값 저장
                    resets.push(new Reset(nx,ny,start));
                }

                run(listCamera,resets,start+1);
                count = Math.min(count,countZero(arr));

                //초기화
                while (true){
                    if(resets.isEmpty() ||resets.peek().start != start) break;
                    Reset bufReset = resets.pop();
                    int resetX = bufReset.x; int resetY = bufReset.y;
                    arr[resetX][resetY] = 0;
                }
            }
        }
        else if(number==2){
            int dx[] = {1,0};
            int dy[] = {0,1};
            for(int dir=0;dir<2;dir++) {
                int nx = x; int ny = y;
                while (nx + dx[dir] >= 0 && nx + dx[dir] < N && ny + dy[dir] >= 0 && ny + dy[dir] < M
                        && arr[nx + dx[dir]][ny + dy[dir]] != 6) {
                    if (arr[nx + dx[dir]][ny + dy[dir]] != 0) {
                        nx = nx + dx[dir]; ny = ny + dy[dir];
                        continue;
                    }
                    nx = nx + dx[dir]; ny = ny + dy[dir];
                    resets.push(new Reset(nx, ny, start));
                    arr[nx][ny] = 7;
                }
                while (nx + -dx[dir] >= 0 && nx + -dx[dir] < N && ny + -dy[dir] >= 0 && ny + -dy[dir] < M
                        && arr[nx + -dx[dir]][ny + -dy[dir]] != 6) {
                    if (arr[nx + -dx[dir]][ny + -dy[dir]] != 0) {
                        nx = nx + -dx[dir]; ny = ny + -dy[dir];
                        continue;
                    }
                    nx = nx + -dx[dir]; ny = ny + -dy[dir];
                    resets.push(new Reset(nx, ny, start));
                    arr[nx][ny] = 7;
                }
                run(listCamera, resets, start + 1);
                count = Math.min(count, countZero(arr));

                //초기화
                while (true) {
                    if (resets.isEmpty() || resets.peek().start != start) break;
                    Reset bufReset = resets.pop();
                    int resetX = bufReset.x;
                    int resetY = bufReset.y;
                    arr[resetX][resetY] = 0;
                }
            }
        }
        else if(number==3){
            int[] dx = {1,-1,-1,1};
            int[] dy = {1,1,-1,-1};
            for(int dir=0;dir<4;dir++){
                int nx = x; int ny = y;
                while (nx + dx[dir]>=0 && nx + dx[dir]< N && arr[nx + dx[dir]][ny]!=6){
                    if(arr[nx + dx[dir]][ny]!=0) {
                        nx = nx + dx[dir];
                        continue;
                    }
                    nx = nx + dx[dir];
                    resets.push(new Reset(nx,ny,start));
                    arr[nx][ny] = 7;
                }
                nx = x; ny =y;
                while (ny + dy[dir]>=0 && ny + dy[dir]< M && arr[nx][ny + dy[dir]]!=6){
                    if(arr[nx][ny + dy[dir]]!=0) {
                        ny = ny + dy[dir];
                        continue;
                    }
                    ny = ny + dy[dir];
                    resets.push(new Reset(nx,ny,start));
                    arr[nx][ny] = 7;
                }

                run(listCamera,resets,start+1);
                count = Math.min(count,countZero(arr));

                //초기화
                while (true){
                    if(resets.isEmpty() ||resets.peek().start != start) break;
                    Reset bufReset = resets.pop();
                    int resetX = bufReset.x; int resetY = bufReset.y;
                    arr[resetX][resetY] = 0;
                }
            }
        }
        else if(number==4){
            int[] dx = {1,-1,1,1};
            int[] dy = {1,1,1,-1};
            for(int dir=0;dir<4;dir++) {
                int nx = x; int ny = y;
                if(dir<2){
                    while (nx + dx[dir]>=0 && nx + dx[dir]< N && arr[nx + dx[dir]][ny]!=6){
                        if(arr[nx + dx[dir]][ny]!=0) {
                            nx = nx + dx[dir];
                            continue;
                        }
                        nx = nx + dx[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                    nx = x; ny =y;
                    while (ny + dy[dir]>=0 && ny + dy[dir]< M && arr[nx][ny + dy[dir]]!=6){
                        if(arr[nx][ny + dy[dir]]!=0) {
                            ny = ny + dy[dir];
                            continue;
                        }
                        ny = ny + dy[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                    nx = x; ny = y;
                    while (ny + -dy[dir]>=0 && ny + -dy[dir]< M && arr[nx][ny + -dy[dir]]!=6){
                        if(arr[nx][ny + -dy[dir]]!=0) {
                            ny = ny + -dy[dir];
                            continue;
                        }
                        ny = ny + -dy[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                }
                else{
                    while (nx + dx[dir]>=0 && nx + dx[dir]< N && arr[nx + dx[dir]][ny]!=6){
                        if(arr[nx + dx[dir]][ny]!=0) {
                            nx = nx + dx[dir];
                            continue;
                        }
                        nx = nx + dx[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                    nx = x; ny =y;
                    while (ny + dy[dir]>=0 && ny + dy[dir]< M && arr[nx][ny + dy[dir]]!=6){
                        if(arr[nx][ny + dy[dir]]!=0) {
                            ny = ny + dy[dir];
                            continue;
                        }
                        ny = ny + dy[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                    nx = x; ny =y;
                    while (nx + -dx[dir]>=0 && nx + -dx[dir]< N && arr[nx + -dx[dir]][ny]!=6){
                        if(arr[nx + -dx[dir]][ny]!=0) {
                            nx = nx + -dx[dir];
                            continue;
                        }
                        nx = nx + -dx[dir];
                        resets.push(new Reset(nx,ny,start));
                        arr[nx][ny] = 7;
                    }
                }

                run(listCamera,resets,start+1);
                count = Math.min(count,countZero(arr));

                //초기화
                while (true){
                    if(resets.isEmpty() ||resets.peek().start != start) break;
                    Reset bufReset = resets.pop();
                    int resetX = bufReset.x; int resetY = bufReset.y;
                    arr[resetX][resetY] = 0;
                }
            }
        }
        else if(number==5){
            int dx = 1; int dy = 1;
            int nx = x; int ny = y;
            while (nx + dx>=0 && nx + dx< N && arr[nx + dx][ny]!=6){
                if(arr[nx + dx][ny]!=0) {
                    nx = nx + dx;
                    continue;
                }
                nx = nx + dx;
                resets.push(new Reset(nx,ny,start));
                arr[nx][ny] = 7;
            }
            nx = x; ny =y;
            while (ny + dy>=0 && ny + dy< M && arr[nx][ny + dy]!=6){
                if(arr[nx][ny + dy]!=0) {
                    ny = ny + dy;
                    continue;
                }
                ny = ny + dy;
                resets.push(new Reset(nx,ny,start));
                arr[nx][ny] = 7;
            }
            nx = x; ny =y;
            while (nx + -dx>=0 && nx + -dx< N && arr[nx + -dx][ny]!=6){
                if(arr[nx + -dx][ny]!=0) {
                    nx = nx + -dx;
                    continue;
                }
                nx = nx + -dx;
                resets.push(new Reset(nx,ny,start));
                arr[nx][ny] = 7;
            }
            nx = x; ny =y;
            while (ny + -dy>=0 && ny + -dy< M && arr[nx][ny + -dy]!=6){
                if(arr[nx][ny + -dy]!=0) {
                    ny = ny + -dy;
                    continue;
                }
                ny = ny + -dy;
                resets.push(new Reset(nx,ny,start));
                arr[nx][ny] = 7;
            }


            run(listCamera,resets,start+1);
            count = Math.min(count,countZero(arr));


            //초기화
            while (true){
                if(resets.isEmpty() ||resets.peek().start != start) break;
                Reset bufReset = resets.pop();
                int resetX = bufReset.x; int resetY = bufReset.y;
                arr[resetX][resetY] = 0;
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

//값을 초기화 해줄 클래스
class Reset{
    public int x,y,start;
    public Reset(int x,int y,int start){
        this.x = x; this.y =y; this.start = start;
    }
}