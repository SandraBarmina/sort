package sort;

import java.util.ArrayList;

public interface Sorting<T extends Comparable> {
    void sort(ArrayList<T> elements, SortingType sortingType);
}