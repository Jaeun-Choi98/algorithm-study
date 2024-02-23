package bruteforce.배열돌리기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] datas;
    static int[][] rotatPos;
    static boolean[] check;
    static int[] rotP;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        datas = new int[n][m];
        // sRow, sCol, eRow, eCol순
        rotatPos = new int[k][4];
        check = new boolean[k];
        rotP = new int[k];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                datas[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            rotatPos[i][0] = r-s-1;
            rotatPos[i][1] = c-s-1;
            rotatPos[i][2] = r+s-1;
            rotatPos[i][3] = c+s-1;
        }
        res = Integer.MAX_VALUE;
        search(0);
        System.out.println(res);
    }
    public static void search(int cur){
        if(cur==k){
            int[][] nbuf = new int[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++) nbuf[i][j] = datas[i][j];
            }
            rotat(nbuf);
            for(int i=0;i<n;i++){
                int sum = 0;
                for(int j=0;j<m;j++){
                    sum += nbuf[i][j];
                    //System.out.print(nbuf[i][j]);
                }
                //System.out.println("");
                res = Math.min(res,sum);
            }
            return;
        }

        for(int i=0;i<k;i++){
            if(check[i]) continue;
            check[i] = true;
            rotP[cur] = i;
            search(cur+1);
            check[i] = false;
        }
    }

    public static void rotat(int[][] buf){
        for(int i=0;i<k;i++){
            int s1 = rotatPos[rotP[i]][0];
            int s2 = rotatPos[rotP[i]][1];
            int e1 = rotatPos[rotP[i]][2];
            int e2 = rotatPos[rotP[i]][3];
            for(int j=0;j<(e1-s1)/2;j++){
                // 위->오른쪽->아래->왼쪽 순
                int tmp1,tmp2;
                int sb1,sb2,eb1,eb2;
                sb1 = e1-j;
                sb2 = s2+j;
                eb1 = s1+j;
                tmp1 = buf[eb1][sb2];
                for(int l=eb1;l<sb1;l++) buf[l][sb2] = buf[l+1][sb2];

                sb1 = s1+j;
                sb2 = s2+j;
                eb2 = e2-j;
                tmp2 = buf[sb1][eb2];
                for(int l=eb2;l>sb1;l--) buf[sb1][l] = buf[sb1][l-1];
                buf[sb1][sb2+1] = tmp1;

                sb1 = s1+j;
                sb2 = e2-j;
                eb1 = e1-j;
                tmp1 = buf[eb1][sb2];
                for(int l=eb1;l>sb1;l--) buf[l][sb2] = buf[l-1][sb2];
                buf[sb1+1][sb2] = tmp2;

                sb1 = e1-j;
                sb2 = e2-j;
                eb2 = s2+j;
                for(int l=eb2;l<sb2;l++) buf[sb1][l] = buf[sb1][l+1];
                buf[sb1][sb2-1] = tmp1;
            }
        }
    }
}
