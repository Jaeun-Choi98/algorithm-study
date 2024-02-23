package 자료구조;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Queue<Test> queue = new LinkedList<>();
        Stack<Test> stack = new Stack();
        Test test1 = new Test(1,"최");
        Test test2 = new Test(2,"재");
        Test test3 = new Test(3,"운");

        stack.push(test1);
        stack.push(test2);
        stack.push(test3);

        queue.add(test1);
        queue.add(test2);
        queue.add(test3);

        for(int i=0;i<3;i++){
            System.out.print(queue.poll());
            System.out.print(' ');
            System.out.print(stack.pop());
            System.out.println();
        }
    }
}

class Test{
    int id;
    String str;
    public Test(int id, String str){
        this.id = id;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", str='" + str + '\'' +
                '}';
    }
}

