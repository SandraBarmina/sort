import sort.SortingImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayListInteger = new ArrayList<>(Arrays.asList(5, 23,7, 2, 45, 1, 155,45455,3,21,2));
        ArrayList<String> arrayListString = new ArrayList<>(Arrays.asList("Http","Top", "Java", "Tutorial","Dot", "Com"));

        new SortingImpl<String>().sort(arrayListString).forEach(System.out::println);
        new SortingImpl<Integer>().sort(arrayListInteger).forEach(System.out::println);


    }
}
