package 정렬.단어정렬;

import java.util.HashSet;
import java.util.Scanner;


//Function, (?T extend R) 템플릿 공부
public class Main {
    public static void main(String[] args) {
        int N;
        HashSet<String> set = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String buf;

        N = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<N;i++){
            buf = sc.nextLine();
            set.add(buf);
        }

        set.stream().sorted((o1, o2) ->
            {
                if(o1.length()==o2.length()) return o1.compareTo(o2);
                else return o1.length() - o2.length();
            }).forEach(s -> System.out.println(s));

        System.out.println();
    }
}
