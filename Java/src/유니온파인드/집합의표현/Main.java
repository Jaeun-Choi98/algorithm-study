package 유니온파인드.집합의표현;

import java.io.*;
import java.util.StringTokenizer;

/*
유니온파인드
[problem](https://www.acmicpc.net/problem/1717)

57~59번째 줄에서 return findUnion(union[x]) 와 return union[x] = findUnion(union[x])
차이점은 서브 노드들과 루트 노드와의 루팅의 유무 차이이다.
 */

public class Main {
    static int[] union;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        union = new int[n+1];

        for(int i=0;i<=n;i++){
            union[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = findUnion(a);
            b = findUnion(b);

            if(cmd == 0){
                if(a<b){
                    union[b] = a;
                }else{
                    union[a] = b;
                }
            }else{
                if(a == b) bw.write("yes\n");
                else bw.write("no\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int findUnion(int x){
        if(union[x] == x) return union[x];
        //return findUion(union[x])와의 차이점은
        //트리 간에 합쳐지고 난 후, 서브트리의 단말 노드들을 루트 트리로 참조하게 한다는 점이다.
        return union[x] = findUnion(union[x]);
    }
}
