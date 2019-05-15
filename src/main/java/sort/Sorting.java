package sort;

import java.util.ArrayList;

public interface Sorting<T extends Comparable> {
    ArrayList<T> sort(ArrayList<T> elements);
}
