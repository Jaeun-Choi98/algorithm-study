package 스트림;

import java.util.Arrays;

public class train_stream {
    public static void main(String[] args) {
        String[] stringsFilter = {"hi",
                            "hello",
                            "test"};
        // filter는 param로 predicate 함수형 인터페이스를 받는다. predicate<T> 인터페이스는  boolean test(T t) 추상 메서드를 갖는다.
        Arrays.stream(stringsFilter).filter((string)->{
            return string.startsWith("h");
        }).forEach(System.out::println);

        System.out.println();

        String[] stringsSorted = {"hi",
                "hello",
                "test"};
        // sorted는 param로 comparator 함수형 인터페이스를 받는다. comparator<T> 인터페이스는 int compare(T o1, T o2) 추상 메서드를 갖는다.
        Arrays.stream(stringsSorted).sorted((string1, string2)->{
            //return string1.length() - string2.length();
            return string1.compareTo(string2);
        }).forEach(System.out::println);

        System.out.println();

        String[] stringsMap = {"hi",
                "hello",
                "test"};
        // map은 param으로 Function 함수형 인터페이스를 받는다. Function<T, R> 인터페이스는 R apply(T t) 추상 메서드를 갖는다.
        Arrays.stream(stringsMap).map(s->{
            return s.toUpperCase();
        }).forEach(System.out::println);
    }
}
