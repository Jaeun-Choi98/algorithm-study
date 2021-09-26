package 그리디알고리즘.잃어버린괄호;

import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

//String Class split메서드 활용
public class Main {
    public static void main(String[] args) {
        List<Integer> Intlist;
        String[] sub,add;
        String str;
        int buf =0; int sum =Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);

        str = sc.nextLine();
        /*StringTokenizer stringTokenizer = new StringTokenizer(str,"-");
        System.out.println(stringTokenizer.nextElement());
        System.out.println(stringTokenizer.nextElement());*/
        sub = str.split("-");
        for(int i=0;i<sub.length;i++){
            add = sub[i].split("\\+");
            for(int j=0;j<add.length;j++) buf += Integer.parseInt(add[j]);
            if(sum == Integer.MAX_VALUE) sum = buf;
            else sum -= buf;
            buf =0;
        }
        System.out.println(sum);
    }
}
