package 시물레이션.괄호추가하기2;

import java.io.*;


public class Main {
    /*
    bruteforce, 조합
    [problem](https://www.acmicpc.net/problem/16638)
     */


    static int n;
    static char[] data;
    static boolean[] braket;
    static int braketCnt;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = br.readLine().toCharArray();

        if(n==1){
            System.out.println(data[0]);
            return;
        }

        int operateCnt = n/2;

        if(operateCnt%2==0) braketCnt = operateCnt/2;
        else braketCnt = operateCnt/2 + 1;

        braket = new boolean[operateCnt];
        res = cal();

        search(0,0);
        System.out.println(res);
    }

    public static void search(int cnt, int curIdx){
        if(cnt == braketCnt) return;
        for(int i=curIdx;i<braket.length;i++){
            if(i!=0 && braket[i-1]) continue;
            braket[i] = true;
            res = Math.max(cal(),res);
            search(cnt+1,i+1);
            braket[i] = false;
        }
    }

    public static int cal(){
        int[] filter0 = new int[n];
        int[] filter1, filter2;
        int size;
        int sizebuf;
        
        size = n;
        for(int i=0;i<size;i++) {
            if(i%2==0) filter0[i] = data[i] - '0';
            else filter0[i] = data[i];
        }
        
        //////////////// 괄호
        sizebuf = 0;
        for(int i=0;i<braket.length;i++){
            if(braket[i]) {
                int idx = 2*i+1;
                size -= 2;
                sizebuf += 2;
                if(filter0[idx]=='+'){
                    filter0[idx] = filter0[idx-1] + filter0[idx+1];
                    filter0[idx-1] = Integer.MIN_VALUE;
                    filter0[idx+1] = Integer.MIN_VALUE;
                }else if(filter0[idx]=='-'){
                    filter0[idx] = filter0[idx-1] - filter0[idx+1];
                    filter0[idx-1] = Integer.MIN_VALUE;
                    filter0[idx+1] = Integer.MIN_VALUE;
                }else if(filter0[idx] =='*'){
                    filter0[idx] = filter0[idx-1] * filter0[idx+1];
                    filter0[idx-1] = Integer.MIN_VALUE;
                    filter0[idx+1] = Integer.MIN_VALUE;
                }
            }
        }

        ///////////////// 곱하기
        filter1 = new int[size];
        int idx =0;
        for(int i=0;i<size+sizebuf;i++){
            if(filter0[i] == Integer.MIN_VALUE) continue;
            filter1[idx++] = filter0[i];
        }
        sizebuf = 0;
        for(int i=0;i<size+sizebuf;i++){
            if(i%2==0) continue;
            if(filter1[i] == '*'){
                sizebuf+=2;
                size-=2;
                int right = i+1;
                int left = i-1;
                while (filter1[right] == Integer.MIN_VALUE) right++;
                while (filter1[left] == Integer.MIN_VALUE) left--;
                filter1[i] = filter1[left] * filter1[right];
                filter1[left] = Integer.MIN_VALUE;
                filter1[right] = Integer.MIN_VALUE;
            }
        }

        ////////////////////// 더하기, 빼기

        filter2 = new int[size];
        idx =0;
        for(int i=0;i<size+sizebuf;i++){
            if(filter1[i] == Integer.MIN_VALUE) continue;
            filter2[idx++] = filter1[i];
        }
        
        for(int i=0;i<size;i++){
            if(i%2==0) continue;
            if(filter2[i] == '+'){
                int right = i+1;
                int left = i-1;
                while (filter2[right] == Integer.MIN_VALUE) right++;
                while (filter2[left] == Integer.MIN_VALUE) left--;
                filter2[i] = filter2[left] + filter2[right];
                filter2[left] = Integer.MIN_VALUE;
                filter2[right] = Integer.MIN_VALUE;
            }else if(filter2[i] == '-'){
                int right = i+1;
                int left = i-1;
                while (filter2[right] == Integer.MIN_VALUE) right++;
                while (filter2[left] == Integer.MIN_VALUE) left--;
                filter2[i] = filter2[left] - filter2[right];
                filter2[left] = Integer.MIN_VALUE;
                filter2[right] = Integer.MIN_VALUE;
            }
        }

        int val = 0;
        for(int i=0;i<size;i++){
            if(filter2[i] != Integer.MIN_VALUE){
                val = filter2[i];
            }
        }
        return val;
    }
}
