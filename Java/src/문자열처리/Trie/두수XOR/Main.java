package 문자열처리.Trie.두수XOR;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 Trie 자료구조
 [problem](https://www.acmicpc.net/problem/13505)
 참고한 정답 풀이 블로그(https://loosie.tistory.com/459)
*/

public class Main {
    static int n;
    static int[] datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Trie trie = new Trie();
        for(int i=0;i<n;i++){
            int data = Integer.parseInt(st.nextToken());
            datas[i] = data;
            trie.insert(data);
        }

        int res = Integer.MIN_VALUE;
        for(int data:datas){
            res = Math.max(res, trie.getMaxXOR(data));
        }
        bw.write(res+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

class TrieNode{
    Map<Integer,TrieNode> childNodes;
    public TrieNode(){
        this.childNodes = new HashMap<>();
    }

}

class Trie{
    TrieNode root;
    int h = 29;
    public Trie(){
        this.root = new TrieNode();
    }

    public void insert(int num){
        TrieNode cur = root;
        for(int i=this.h;i>=0;i--){
            int val = num & (1<<i);
            val = val > 0 ? 1 : 0;
            if(cur.childNodes.get(val) == null){
                cur.childNodes.put(val,new TrieNode());
                cur = cur.childNodes.get(val);
            }else{
                cur = cur.childNodes.get(val);
            }
        }
    }

    public int getMaxXOR(int num){
        int res = 0;
        TrieNode cur = root;

        // num 과 XOR 했을 때, 최댓값을 찾는 과정
        // num 이 100 이었을 때, 011인 수가 있는지 찾는 과정에서 XOR 연산을 하면서 내려감
        // 참고) num이 1000 이었을 때, 01??인 수를 찾았으면 00??은 탐색하지 않아도 됨
        for(int i=this.h;i>=0;i--){
            int val = num & (1<<i);
            val = val > 0 ? 0 : 1;
            if(cur.childNodes.get(val)==null){
                val = val == 1 ? 0 : 1;
            }else{
                res += (1<<i);
            }
            cur = cur.childNodes.get(val);
        }
        return res;
    }
}
