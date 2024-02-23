package DFS와BFS.새로운하노이탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    [problem](https://www.acmicpc.net/problem/12906)
     */
    static Stack<Character>[] data;
    static Set<String> check;
    static String ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = new Stack[4];
        for(int i=0;i<4;i++) data[i] = new Stack<>();
        check = new HashSet<>();
        StringTokenizer st;
        ans = "";

        int a,b,c;
        a=b=c=0;

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            if(len ==0 ) continue;
            String buf = st.nextToken();
            char ch;
            for(int j=0;j<len;j++){
                ch = buf.charAt(j);
                data[i].push(ch);
                if(ch=='A') a++;
                else if(ch=='B') b++;
                else c++;
            }
        }

        for(int i=0;i<a;i++) ans+="A";
        ans += "/";
        for(int i=0;i<b;i++) ans+="B";
        ans += "/";
        for(int i=0;i<c;i++) ans+="C";
        ans += "/";


        int res = -1;
        Queue<Stack<Character>[]> que = new LinkedList<>();
        que.add(data);
        check.add(getState(data));
        while (!que.isEmpty()){
            Stack<Character>[] buf = que.poll();
            String state = getState(buf);
            if(state.equals(ans)){
                res = buf[3].size();
                break;
            }
            for(int i=0;i<3;i++){
                if(buf[i].isEmpty()) continue;
                for(int j=0;j<3;j++){
                    if(i==j) continue;
                    buf[j].push(buf[i].pop());
                    String checkBuf = getState(buf);
                    //System.out.println(checkBuf);
                    if(!check.contains(checkBuf)){
                        check.add(checkBuf);
                        buf[3].push('c');
                        que.add(copy(buf));
                        buf[3].pop();
                    }
                    buf[i].push(buf[j].pop());
                }
            }
        }

        System.out.println(res);
        br.close();
    }

    static String getState(Stack<Character>[] cur){
        String buf = "";
        int cnt = 0;
        for(Stack<Character> stack: cur){
            if(cnt == 3) break;
            for(int i=0;i<stack.size();i++){
                buf += stack.get(i);
            }
            buf += "/";
            cnt ++;
        }
        return buf;
    }

    static Stack<Character>[] copy(Stack<Character>[] cur){
        Stack<Character>[] cp = new Stack[4];
        for(int i=0;i<4;i++) {
            cp[i] = new Stack<>();
            for(int j=0;j<cur[i].size();j++){
                cp[i].push(cur[i].get(j));
            }
        }
        return cp;
    }
}
