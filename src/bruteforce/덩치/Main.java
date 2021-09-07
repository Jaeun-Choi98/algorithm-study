package bruteforce.덩치;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numPerson;
        int bufWeight, bufHeight;
        List<Person> people = new ArrayList<>();
        List<Integer> rankPeople = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        numPerson = sc.nextInt();
        for(int i=0;i<numPerson;i++){
            bufWeight = sc.nextInt(); bufHeight = sc.nextInt();
            people.add(new Person(bufWeight,bufHeight));
        }
        run(people,rankPeople);
        printList(rankPeople);
    }

    public static void run(List<Person> people, List<Integer> rankPeople){
        int count;
        for(int i=0;i<people.size();i++){
            count = 0;
            for(int j=0;j<people.size();j++){
                if(i==j) continue;
                if(people.get(i).getWeight() < people.get(j).getWeight() &&
                        people.get(i).getHeight() < people.get(j).getHeight()){
                    count++;
                }
            }
            rankPeople.add(count+1);
        }
    }

    public static <T> void printList(List<T> list){
        for(int i=0;i<list.size();i++) {
            System.out.print(list.get(i));
            System.out.print(' ');
        }
    }
}

class Person{
    int weight;
    int height;

    public Person(int w, int h){
        weight = w; height =h;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
