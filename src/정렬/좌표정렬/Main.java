package 정렬.좌표정렬;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<XY> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        for(int i=0;i<N;i++){
            int j,k;
            j=sc.nextInt(); k=sc.nextInt();
            XY xy = new XY(j,k);
            arrayList.add(xy);
        }
        //리버스 정렬
        //arrayList.stream().map(xy -> xy.reverse()).sorted().map(xy -> xy.reverse()).forEach(x-> System.out.println(x));
        arrayList.stream().sorted().forEach(x-> System.out.println(x));
    }
}

class XY implements Comparable<XY> {
    int x; int y;

    public XY(int x, int y){
        this.x=x; this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public XY reverse(){
        this.x *= -1;
        this.y *= -1;
        return this;
    }

    @Override
    public int compareTo(XY o) {
        if(this.x == o.getX()) return this.y-o.getY();
        else return this.x-o.x;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}