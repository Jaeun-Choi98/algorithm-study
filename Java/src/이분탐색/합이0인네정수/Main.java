package 이분탐색.합이0인네정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/7453)
    이분 탐색 방법으로 혼자서 해결하지 못해 https://bleron.tistory.com/164 를 참고하였음..
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] CD = new int[n*n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[idx++] = C[i] + D[j];
            }
        }
        Arrays.sort(CD);

        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = A[i] + B[j];
                int upper = upperBound(-temp, CD);
                int lower = lowerBound(-temp, CD);
                res += upper - lower + 1;
            }
        }
        System.out.println(res);
    }
    static int upperBound(int key, int[] arr) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    static int lowerBound(int key, int[] arr) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] >= key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
