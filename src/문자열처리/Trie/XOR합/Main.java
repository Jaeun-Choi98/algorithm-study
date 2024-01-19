package 문자열처리.Trie.XOR합;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc!=0){
            int n = Integer.parseInt(br.readLine());
            int[] data = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            Trie trie = new Trie();
            trie.insert(0);
            for(int i=1;i<=n;i++){
                data[i] = data[i-1]^Integer.parseInt(st.nextToken());
                trie.insert(data[i]);
            }

            int res = -1;
            for(int i : data){
                res = Math.max(res,trie.search(i));
            }

            bw.write(res+"\n");
            tc--;
        }

        br.close();
        bw.flush();
        bw.close();
    }
}

class TrieNode{
    Map<Character, TrieNode> childNodes;

    public TrieNode(){
        childNodes = new HashMap<>();
    }
}

class Trie{
    TrieNode root;
    int h = 30;
    public Trie(){
        root = new TrieNode();
    }

    public void insert(int num){
        TrieNode cur = root;
        for(int i=h;i>=0;i--){
            int val = num & (1<<i);
            char valToCh = val > 0 ? '1' : '0';
            //val = val > 0 ? 1 : 0;
            if(cur.childNodes.get(valToCh) == null) cur.childNodes.put(valToCh,new TrieNode());
            cur = cur.childNodes.get(valToCh);
        }
    }

    public int search(int num){
        int res = 0;
        TrieNode cur = root;
        for(int i=h;i>=0;i--){
            int val = num & (1<<i);
            char valToCh = val > 0 ? '0' : '1';
            //val = val > 0 ? 0 : 1;
            if(cur.childNodes.get(valToCh)==null){
                valToCh = valToCh == '1' ? '0': '1';
            }else{
                res += (1<<i);
            }
            cur = cur.childNodes.get(valToCh);
        }
        return res;
    }

}
