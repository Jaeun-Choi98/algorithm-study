package 정렬.나이순정렬;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        String bufName; int bufAge;
        Scanner sc = new Scanner(System.in);
        List<Info> list = new ArrayList<>();

        N = sc.nextInt();
        for(int i=0;i<N;i++){
            bufAge = sc.nextInt();
            bufName = sc.nextLine();
            bufName = bufName.substring(1);
            list.add(new Info.Builder()
                    .age(bufAge)
                    .name(bufName)
                    .build());
        }

        list.stream().sorted((o1, o2) -> o1.age-o2.age).forEach(System.out::println);
    }
}

class Info{
    int age;
    String name;

    private Info(Builder builder){
        this.age = builder.age;
        this.name = builder.name;
    }

    public static class Builder{
        private int age;
        private String name;

        public Builder(){}
        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Info build(){
            return new Info(this);
        }

    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.age + " " + this.name;
    }
}