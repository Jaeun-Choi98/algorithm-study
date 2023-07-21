package 비트마스크.불끄기;

import java.io.*;

public class Main {
/*
BitMask, 그리디
[problem](https://www.acmicpc.net/problem/14939)
 */
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] bit = new int[10];

        for(int i=0;i<10;i++){
            String str = br.readLine();
            for(int j=0;j<10;j++){
                if(str.charAt(j)=='O'){
                    bit[i] = bit[i] | 1<<j;
                }
            }
        }

        int[] dx = {0,0,0,1,-1};
        int[] dy = {0,1,-1,0,0};

        int result = Integer.MAX_VALUE;
        int cnt = 0;
        int[] copyBit = new int[10];

        for(int k=0;k<1024;k++){
            cnt =0;
            for(int i=0;i<10;i++){
                copyBit[i] = bit[i];
            }

            //첫 번째 줄로 모든 경우의 따지기 0000000000~1111111111 (총 1024가지, 2^10)
            for(int i=0;i<10;i++){
                if((k&(1<<i))>0){
                    cnt++;
                    for(int j=0;j<5;j++){
                        int nx = 0 + dx[j];
                        int ny = i + dy[j];
                        if(nx<0||ny<0||nx>=10||ny>=10) continue;
                        copyBit[nx] = (copyBit[nx] ^ (1<<ny));
                    }
                }
            }

            //1~9행 불 끄기
            for(int i=0;i<9;i++){
                for(int j=0;j<10;j++){
                    if((copyBit[i]&(1<<j))>0){
                        cnt++;
                        for(int l=0;l<5;l++){
                            int nx = i+1 +dx[l];
                            int ny = j +dy[l];
                            if(nx<0||ny<0||nx>=10||ny>=10) continue;
                            copyBit[nx] = (copyBit[nx] ^ (1<<ny));
                        }
                    }
                }
            }

            //10행 불도 꺼져있다면 가능한 경우의 수
            if(copyBit[9] == 0) {
                result = Math.min(result,cnt);
            }

        }


        if(result==Integer.MAX_VALUE) result = -1;
        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

}
