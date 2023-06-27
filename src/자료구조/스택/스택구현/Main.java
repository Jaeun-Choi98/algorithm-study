package 자료구조.스택.스택구현;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int commandNum;
        String command;
        Scanner sc = new Scanner(System.in);
        MyStack myStack = new MyStack();
        StringBuilder stringBuilder = new StringBuilder();

        commandNum = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<commandNum;i++){
            command = sc.nextLine();
            if(command.split(" ")[0].equals("push")) myStack.push(Integer.parseInt(command.split(" ")[1]));
            else if(command.equals("pop")) stringBuilder.append(myStack.pop()).append("\n");
            else if(command.equals("size")) stringBuilder.append(myStack.size()).append("\n");
            else if(command.equals("top")) stringBuilder.append(myStack.top()).append("\n");
            else if(command.equals("empty")) {
                if(myStack.empty()) stringBuilder.append(1).append("\n");
                else stringBuilder.append(0).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }
}

class MyStack{
    List<Integer> list;
    int idx;

    public MyStack(){
        list = new ArrayList<>();
        idx = -1;
    }

    public void push(int n){
        list.add(++idx,n);
    }

    public Integer pop(){
        if(idx == -1) return -1;
        return list.get(idx--);
    }

    public Integer top(){
        if(idx == -1) return -1;
        return list.get(idx);
    }

    public int size(){
        return idx+1;
    }

    public boolean empty(){
        if(idx == -1) return true;
        else return false;
    }

}
