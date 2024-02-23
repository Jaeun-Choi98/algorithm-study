package 백트래킹.Guess;
import java.io.*;

public class Main {
    /*
    백트래킹
    [problem](https://www.acmicpc.net/problem/1248)
     */
    static int n;
    static int[][] map;
    static int[] res;
    static int[] sum;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        res = new int[n];
        sum = new int[n];

        String buf = br.readLine();
        int row = 0;
        int cut = n;
        int cur = 0;
        while(buf.length() != cur){
            for(int i=cur;i<cur+cut;i++){
                char tmp = buf.charAt(i);
                int col = i - cur;
                if(tmp == '+'){
                    map[row][col+row] = 1;
                }else if(tmp == '-'){
                    map[row][col+row] = -1;
                }else {
                    map[row][col+row] = 0;
                }
            }
            cur += cut;
            cut--;
            row++;
        }

        /*for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                bw.write(map[i][j] + " ");
            }
            bw.write("\n");
        }*/
        flag = false;
        search(0);
        for(int i=0;i<n;i++){
            bw.write(res[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt){
        if(cnt == n){
            flag = true;
            return;
        }
        if(map[cnt][cnt] == 0){
            res[cnt] = 0;
            if(cnt-1>=0) sum[cnt] = sum[cnt-1];
            search(cnt+1);
        }else if(map[cnt][cnt] <0){
            for(int i=1;i<11;i++){
                if(flag) return;
                res[cnt] = -i;
                if(cnt-1>=0) sum[cnt] = sum[cnt-1] + res[cnt];
                else sum[cnt] = res[cnt];
                if(!check(cnt)) continue;
                search(cnt+1);
            }
        }else{
            for(int i=1;i<11;i++){
                if(flag) return;
                res[cnt] = i;
                if(cnt-1>=0) sum[cnt] = sum[cnt-1] + res[cnt];
                else sum[cnt] = res[cnt];
                if(!check(cnt)) continue;
                search(cnt+1);
            }
        }
    }

    public static boolean check(int cnt){
        int buf = sum[cnt];
        if(map[0][cnt] < 0 && buf >= 0) return false;
        else if(map[0][cnt] > 0 && buf <= 0) return false;
        else if(map[0][cnt] == 0 && buf != 0) return false;


        for(int i=0;i<cnt-1;i++){
            buf -= res[i];
            if(map[i+1][cnt] < 0 && buf >= 0) return false;
            else if(map[i+1][cnt] > 0 && buf <= 0) return false;
            else if(map[i+1][cnt] == 0 && buf != 0) return false;
        }
        return true;
    }
}
