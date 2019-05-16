package sort;

import java.util.ArrayList;

public interface Sorting<T extends Comparable> {
    void sortAscending(ArrayList<T> elements);
    void sortDescending(ArrayList<T> elements);
}