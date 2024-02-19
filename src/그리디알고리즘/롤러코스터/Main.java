package 그리디알고리즘.롤러코스터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    [problem](https://www.acmicpc.net/problem/2873)
    참고블로그(https://se-jung-h.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98C-%EB%B0%B1%EC%A4%80-2873-%EB%A1%A4%EB%9F%AC%EC%BD%94%EC%8A%A4%ED%84%B0)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int r,c;
        int[][] data;
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new int[r][c];
        int[] minValPos = new int[2];
        int minVal = 1001;
        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<c;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] < minVal && (i+j)%2==1) {
                    minVal = data[i][j];
                    minValPos[0] = i;
                    minValPos[1] = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if(r%2==1){
            for(int i=0;i<r;i++){
                for(int j=0;j<c-1;j++){
                    if (i%2==1) sb.append("L");
                    else sb.append("R");
                }
                if(i==r-1) continue;
                sb.append("D");
            }
        }else if(c%2==1){
            for(int i=0;i<c;i++){
                for(int j=0;j<r-1;j++){
                    if(i%2==1) sb.append("U");
                    else sb.append("D");
                }
                if(i==c-1) continue;
                sb.append("R");
            }
        }else{
            int r1 = minValPos[0]%2==0?minValPos[0]:minValPos[0]-1;
            for(int i=0;i<r1;i++){
                for(int j=0;j<c-1;j++){
                    if (i%2==1) sb.append("L");
                    else sb.append("R");
                }
                sb.append("D");
            }

            for(int i=0;i<minValPos[1];i++){
                if(i%2==0) sb.append("DR");
                else sb.append("UR");
            }
            for(int i=minValPos[1];i<c-1;i++){
                if(i%2==0) sb.append("RD");
                else sb.append("RU");
            }

            r1 += 2;
            for(int i=r1;i<r;i++){
                sb.append("D");
                for(int j=0;j<c-1;j++){
                    if(i%2==0) sb.append("L");
                    else sb.append("R");
                }
            }

        }
        System.out.println(sb);
        br.close();
    }
}
