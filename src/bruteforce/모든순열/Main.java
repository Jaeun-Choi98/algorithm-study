package bruteforce.모든순열;

import java.io.*;
import java.util.ArrayList;

public class Main {
    /*
    bruteforce, bitmask
    [problem](https://www.acmicpc.net/problem/10974)
     */
    static int n;
    static BufferedWriter bw;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        search(0);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void search(int bit) throws IOException {

        if(bit == (1<<n)-1){
            for(int i=0;i<n;i++){
                bw.write(list.get(i) + " ");
            }
            bw.write("\n");
        }

        for(int i=0;i<n;i++){
            if((bit&1<<i) > 0) continue;
            list.add(i+1);
            search(bit|1<<i);
            list.remove(list.size()-1);
        }
    }
}
