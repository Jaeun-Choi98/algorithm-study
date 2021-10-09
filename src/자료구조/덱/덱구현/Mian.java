package 자료구조.덱.덱구현;

public class Mian {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        Deq deq = new Deq(2);
        deq.pushFront(1);
        deq.pushBack(2);
        deq.pushBack(3);
        deq.pushFront(3);
        System.out.println(deq.popBack());
        deq.pushBack(3);
        System.out.println(deq.popFront());
        System.out.println(deq.popFront());
    }
}

class Deq{
    int MX;
    int[] data;
    int head, tail, deqSize;
    public Deq(int size){
        MX = size/2;
        deqSize = (size%2==0)? 2*MX: 2*MX +1;
        data = new int[deqSize];
        head = tail = MX;
    }

    public boolean isEmpty(){
        if(head == tail) return true;
        else return false;
    }

    public void pushFront(int x){
        if(head-1<0) {
            System.out.println("Front space is fully charged");
            return;
        }
        data[--head] = x;
    }

    public void pushBack(int x){
        if(tail>=deqSize) {
            System.out.println("Back space is fully charged");
            return;
        }
        data[tail++] = x;
    }

    public int popFront(){
        if(isEmpty()) {
            System.out.println("Deq is empty");
            return 0;
        }
        return data[head++];
    }

    public int popBack(){
        if(isEmpty()) {
            System.out.println("Deq is empty");
            return 0;
        }
        return data[--tail];
    }

    public int front(){
        if(isEmpty()) {
            System.out.println("Deq is empty");
            return 0;
        }
        return data[head];
    }

    public int back(){
        if(isEmpty()) {
            System.out.println("Deq is empty");
            return 0;
        }
        return data[tail];
    }

}
