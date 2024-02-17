package 문자열처리.Trie.문자열집합판별;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,q;
    static String[] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();
        n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) trie.insert(br.readLine());
        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            if(trie.search(br.readLine())) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
        br.close();
    }
}

class TrieNode{
    Map<Character,TrieNode> childNode;
    boolean isWord;
    public TrieNode(){
        childNode = new HashMap<>();
        isWord = false;
    }
}

class Trie{
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode cur = root;
        int len = word.length();
        Character ch;
        for(int i=0;i<len;i++){
            ch = word.charAt(i);
            if(cur.childNode.get(ch) == null) cur.childNode.put(ch,new TrieNode());
            cur = cur.childNode.get(ch);
        }
        cur.isWord = true;
    }

    public boolean search(String word){
        boolean flag = false;
        int len = word.length();
        Character ch;
        TrieNode cur;
        for(int i=0;i<len;i++){
            ch = word.charAt(i);
            if(root.childNode.get(ch)!=null){
                cur = root;
                for(int j=i;j<len;j++){
                    ch = word.charAt(j);
                    cur = cur.childNode.get(ch);
                    if(cur == null) break;
                    if(cur.isWord) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) break;
        }
        return flag;
    }

}