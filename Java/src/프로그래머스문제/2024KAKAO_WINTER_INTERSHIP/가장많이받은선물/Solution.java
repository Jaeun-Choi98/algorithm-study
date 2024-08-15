import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 지수를 저장할 배열
        Map<String,Integer> friendsNum = new HashMap<>();
        int[] point = new int[friends.length];
        int[] res = new int[friends.length];
        int[][] data = new int[friends.length][friends.length];
        int idx = 0;
        for (String str : friends) {
            friendsNum.put(str,idx++);
        }
        for (String str : gifts){
            String[] strSplit = str.split(" ");
            int from = friendsNum.get(strSplit[0]);
            int to = friendsNum.get(strSplit[1]);
            point[from]++; point[to]--;
            data[from][to]++;
        }
        
        for (int i=0;i<friends.length;i++){
            for (int j=i+1;j<friends.length;j++){
                if(data[i][j] < data[j][i]){
                    res[j]++;
                }else if(data[i][j] > data[j][i]){
                    res[i]++;
                }else{
                    if(point[i] < point[j]){
                        res[j]++;
                    }else if(point[i]>point[j]){
                        res[i]++;
                    }
                }
            }
        }
        
        for (int i:res){
            answer = Math.max(answer,i);
        }
        return answer;
    }
}