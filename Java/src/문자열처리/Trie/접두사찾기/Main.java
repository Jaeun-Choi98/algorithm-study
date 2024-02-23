package 문자열처리.Trie.접두사찾기;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    /*
    문자열, Tire 알고리즘
    [problem](https://www.acmicpc.net/problem/14426)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N,M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int res = 0;

        Trie trie = new Trie();
        for(int i=0;i<N;i++){
            trie.insert(br.readLine());
        }
        for(int i=0;i<M;i++){
            if(trie.contains(br.readLine())) res++;
        }

        bw.write(res+"\n");
        br.close();
        bw.flush();
        bw.close();
    }

}

class TrieNode{
    Map<Character, TrieNode> childNodes = new HashMap<>();
    boolean isLast;
}

class Trie{
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }
    void insert(String word){
        TrieNode cur = this.root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(cur.childNodes.get(ch)==null){
                cur.childNodes.put(ch,new TrieNode());
                cur = cur.childNodes.get(ch);
            }else{
                cur = cur.childNodes.get(ch);
            }
        }
        cur.isLast = true;
    }

    boolean contains(String word){
        TrieNode cur = this.root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            cur = cur.childNodes.get(ch);
            if(cur == null){
                return false;
            }
        }
        return true;
    }
}
