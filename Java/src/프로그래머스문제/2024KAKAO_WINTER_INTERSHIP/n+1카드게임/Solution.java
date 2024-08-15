import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 1;
        List<Integer> hasCards = new ArrayList<>();
        List<Integer> nextCards = new ArrayList<>();
        int leng = cards.length;
        for (int i=0;i<leng/3;i++){
            hasCards.add(cards[i]);
        }
        boolean isPossible;
        for (int l=leng/3;l<leng;l+=2){
            //System.out.println(coin);
            nextCards.add(cards[l]);
            nextCards.add(cards[l+1]);
            // 조건에 만족한다면 다음 라운드 아니라면 종료
            isPossible = false;
            for(int i=0;i<hasCards.size();i++){
                if(isPossible) break;
                for(int j=i+1;j<hasCards.size();j++){
                    if(hasCards.get(i) + hasCards.get(j) == leng+1){
                        answer++;
                        //System.out.printf("%d %d\n",hasCards.get(i),hasCards.get(j));
                        hasCards.remove(i);
                        hasCards.remove(j-1);
                        isPossible = true;
                        break;
                    }
                }
            }
            if(isPossible) continue;
            if(coin>=1) {
                for(int i=0;i<nextCards.size();i++){
                    if(isPossible) break;
                    for(int j=0;j<hasCards.size();j++){
                        if(nextCards.get(i) + hasCards.get(j) == leng+1){
                            answer++;
                            //System.out.printf("%d %d\n",nextCards.get(i),hasCards.get(j));
                            nextCards.remove(i);
                            hasCards.remove(j);
                            isPossible = true;
                            coin-=1;
                            break;
                        }
                    }
                }   
            }
            
            if(isPossible) continue;
            if(coin>=2){
                for(int i=0;i<nextCards.size();i++){
                    if(isPossible) break;
                    for (int j=i+1;j<nextCards.size();j++){
                        if(nextCards.get(i) + nextCards.get(j) == leng+1){
                            answer++;
                            //System.out.printf("%d %d\n",nextCards.get(i),nextCards.get(j));
                            nextCards.remove(i);
                            //System.out.println(nextCards.size());
                            nextCards.remove(j-1);
                            isPossible = true;
                            coin-=2;
                            break;
                        }
                    }
                }
                
            }
            if(!isPossible) break;
        }
        return answer;
    }
}