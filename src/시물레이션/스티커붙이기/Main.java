package 시물레이션.스티커붙이기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int N,M,stickerNum;
    static Scanner sc = new Scanner(System.in);
    static List<Sticker> list = new ArrayList<>();

    public static void main(String[] args) {

        int result =0;
        init(); //Input Data 받아오기

        for(int i=0;i<list.size();i++){
            Sticker sticker = list.get(i);
            boolean flag = false;

            for(int rotationNum=0;rotationNum<4;rotationNum++){

                for(int j=0;j<N;j++) {
                    for (int k = 0; k < M; k++) {
                        if (j + sticker.r <= N && k + sticker.c <= M && isPaste(j, k, sticker)) {
                            paste(j, k, sticker);
                            flag = true;
                            break;
                        }
                    }
                    if(flag) break;
                }
                if(flag) break;
                sticker = rotate(sticker);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j] == 1)
                    result++;
            }
        }

        System.out.println(result);
        /*System.out.println();
        Arrays.stream(arr).forEach(ints -> {
            Arrays.stream(ints).forEach(System.out::print);
            System.out.println();
        });*/
    }

    public static void init(){
        N = sc.nextInt(); M = sc.nextInt(); stickerNum = sc.nextInt();
        arr = new int[N][M];

        for(int i=0;i<stickerNum;i++){
            int l = sc.nextInt();
            int n = sc.nextInt();
            Sticker buf = new Sticker(l,n);

            for(int j=0;j<l;j++){
                for(int k=0;k<n;k++){
                    buf.putData(j,k,sc.nextInt());
                }
            }
            list.add(buf);
        }
    }

    public static boolean isPaste(int x, int y, Sticker sticker){
        for(int i=0;i<sticker.r;i++){
            for(int j=0;j<sticker.c;j++){
                if(arr[x+i][y+j] + sticker.arr[i][j] == 2) return false;
            }
        }
        return true;
    }

    public static void paste(int x, int y, Sticker sticker){
        for(int i=0;i<sticker.r;i++){
            for(int j=0;j<sticker.c;j++){
                if(sticker.arr[i][j] == 1)
                    arr[x+i][y+j] = 1;
            }
        }
    }

    public static Sticker rotate(Sticker sticker){
        Sticker nSticker = new Sticker(sticker.c,sticker.r);
        for(int i=0;i<sticker.c;i++){
            for(int j=0;j<sticker.r;j++){
                if(sticker.arr[sticker.r-1-j][i]==1)
                    nSticker.putData(i,j,1);
            }
        }
        return nSticker;
    }
}

class Sticker{
    int[][] arr;
    int r,c;

    public Sticker(int N, int M){
        this.r = N; this.c = M;
        arr = new int[r][c];
    }

    public void putData(int n, int m, int data){
        arr[n][m] = data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(arr).forEach(ints -> {
            Arrays.stream(ints).forEach(value -> stringBuilder.append(value));
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}

