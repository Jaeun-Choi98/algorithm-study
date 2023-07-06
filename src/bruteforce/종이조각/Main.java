package bruteforce.종이조각;

import java.io.*;
import java.util.StringTokenizer;

//BruteForce(BitMask) + 시물레이션
//[Probelm](https://www.acmicpc.net/problem/14391)

//헤매었던 부분
//모든 경우의 수(BitMask)에서 연속된 0 또는 1을 찾는 부분에서 작은 부분은 배제한다.
//ex) 1111일 경우  111/1, 11/11, 1/111 은 배제해야한다. 가장 큰 수를 찾는 것이기 때문에
//머리가 나빠 이해하는데 오래 걸렸다..;;
public class Main {
    static char[][] map;
    static int result;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i=0;i<n;i++) map[i] = br.readLine().toCharArray();


        result = 0;
        search(0,0);

        bw.write(result+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void search(int bitMask,int cnt){
        if(cnt == n*m){
            //System.out.println(bitMask);
            int sum = 0;
            String str = "";
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if((bitMask & (1<<(i*m+j))) >0){
                        str += map[i][j];
                    }
                    else{
                        if(str.isEmpty()) continue;
                        sum+=Integer.parseInt(str);
                        str = "";
                    }
                }
                if(str.isEmpty()) continue;
                sum += Integer.parseInt(str);
                str="";
            }
            str = "";
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if((bitMask & (1<<(j*m+i))) == 0){
                        str += map[j][i];
                    }
                    else{
                        if(str.isEmpty()) continue;
                        sum += Integer.parseInt(str);
                        str = "";
                    }
                }
                if(str.isEmpty()) continue;
                sum += Integer.parseInt(str);
                str="";
            }
            result = Math.max(result,sum);
            return;
        }
        search(bitMask | 1<<cnt,cnt+1);
        search(bitMask | 0<<cnt, cnt+1);
    }
}
