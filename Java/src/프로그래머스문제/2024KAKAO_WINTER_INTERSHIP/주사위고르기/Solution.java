import java.util.*;

class Solution {
    int[] A,B;
    int size;
    boolean[] check;
    int combIdx;
    Map<Integer,String> saveCombData;
    List<Integer> savePointData;
    public int[] solution(int[][] dice) {
        size = dice.length;
        int[] answer = new int[size/2];
        check = new boolean[size];
        A = new int[size/2];
        B = new int[size/2]; 
        saveCombData = new HashMap<>();
        savePointData = new ArrayList<>();
        combIdx = 0;
        Comb(0,0,dice);
        int tmp = -1;
        int idxTmp = 0;
        for(int i=0;i<savePointData.size();i++){
            //System.out.println(savePointData.get(i));
            if(savePointData.get(i) > tmp){
                tmp = savePointData.get(i);
                idxTmp = i;
            }
        }
        String answerStr = saveCombData.get(idxTmp);
        for (int i=0;i<size/2;i++){
            answer[i] = Integer.parseInt(String.valueOf(answerStr.charAt(i)))+1;
        }
        return answer;
    }
    public void Comb(int idx, int cnt, int[][] dice){
        if (cnt == size/2){
            String combData = "";
            int a,b;
            a = 0;
            b = 0;
            for(int i=0;i<size;i++){
                if(check[i]){
                    A[a++] = i;
                    combData += Integer.toString(i);
                }else {
                    B[b++] = i;
                }
            }
            saveCombData.put(combIdx++,combData);
            Calc(dice);
            return;
        }
        for (int i=idx;i<size;i++){
            check[i] = true;
            Comb(i+1,cnt+1,dice);
            check[i] = false;
        }
    }
    public void Calc(int[][] dice){
        List<Integer> acase = new ArrayList<>();
        List<Integer> bcase = new ArrayList<>();
        Map<Integer,Integer> acaseCnt = new HashMap<>();
        Map<Integer,Integer> bcaseCnt = new HashMap<>();
        CalcCase(acase, bcase, 0, dice, 0, 0,acaseCnt,bcaseCnt);
        int cnt = 0;
        for(int i=0;i<acase.size();i++){
            for(int j=0;j<bcase.size();j++){
                if(acase.get(i) > bcase.get(j)){
                    cnt+= acaseCnt.get(acase.get(i)) * bcaseCnt.get(bcase.get(j));
                }
            }
        }
        savePointData.add(cnt);
    }
    public void CalcCase(List<Integer> a, List<Integer> b, int idx, int[][] dice, int sumA, int sumB, Map<Integer,Integer> acaseCnt, Map<Integer,Integer> bcaseCnt){
        if (idx == size/2) {
            if(acaseCnt.containsKey(sumA)){
                acaseCnt.put(sumA,acaseCnt.get(sumA)+1);
            }else{
                acaseCnt.put(sumA,1);
                a.add(sumA);
            }
            if(bcaseCnt.containsKey(sumB)){
                bcaseCnt.put(sumB,bcaseCnt.get(sumB)+1);
            }else{
                bcaseCnt.put(sumB,1);
                b.add(sumB);
            }
            return;
        }
        for(int i=0;i<6;i++){
            CalcCase(a,b,idx+1,dice,sumA+dice[A[idx]][i], sumB+dice[B[idx]][i],acaseCnt , bcaseCnt);
        }
    }
}