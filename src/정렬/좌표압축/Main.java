package 정렬.좌표압축;

import java.util.*;

//시간 초과 에러...
//https://girawhale.tistory.com/38 솔루션 참고
public class Main {
    public static void main(String[] args) {
        int N, bufNum;
        int[] arr, copyArr;
        Scanner sc = new Scanner(System.in);
        Map<Integer,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            bufNum = sc.nextInt();
            arr[i] = bufNum;
        }
        copyArr = arr.clone();
        Arrays.sort(copyArr);
        int idx=0;
        for(int n :copyArr){
            if(!map.containsKey(n))
                map.put(n,idx++);
        }

        for(int i=0;i<N;i++){
            sb.append(map.get(arr[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}


