import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        List<List<Integer>> graph = new ArrayList<>();
        int maxVertax = 0;
        // 인접리스트에 저장
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            maxVertax = Math.max(maxVertax, from);
            maxVertax = Math.max(maxVertax, to);
            while(from >= graph.size()){
                graph.add(new ArrayList<>());
            }
            graph.get(from).add(to);
        }
        // 생성정점 찾기
        boolean[] visited = new boolean[maxVertax+1];
        for(int i=0;i<graph.size();i++){
            for (Integer from : graph.get(i)){
                visited[from] = true;
            }
            if(graph.get(i).size() <= 1) {
                visited[i] = true;
            }
        }
        for (int i=0;i<visited.length;i++){
            if(!visited[i]) {
                answer[0] = i;
            }
            visited[i] = false;
        }
        // 도넛, 막대, 8자
        for(Integer to : graph.get(answer[0])){
            dfs(to,visited, answer, graph);
        }
        
        return answer;
    }
    
    public void dfs(int s, boolean[] visited, int[] answer, List<List<Integer>> graph){
        visited[s] = true;
        List<Integer> edges = graph.get(s);
        if (edges.size() == 0) {
            answer[2]++;
        }else if(edges.size() == 1) {
            if(visited[edges.get(0)]){
                answer[1]++;
            }else {
                dfs(edges.get(0),visited,answer, graph);
            }
        }else if(edges.size() == 2){
            answer[3]++;
        }
    }
}