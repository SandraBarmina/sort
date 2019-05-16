package sort;

import java.util.ArrayList;

public class SortingImpl<T extends Comparable> implements Sorting<T> {

    public void sortAscending(ArrayList<T> elements) {
        for (int i = 1; i < elements.size(); i++) {
            T key = elements.get(i);
            for (int j = i - 1; j >= 0 && (key.compareTo(elements.get(j)) < 0); j--) {
                elements.add(j, elements.get(j + 1));
                elements.remove(j + 2);
            }
        }
    }

    public void sortDescending(ArrayList<T> elements) {
        for (int i = elements.size() - 2; i >= 0; i--) {
            T key = elements.get(i);
            for (int j = i + 1; j < elements.size() && (key.compareTo(elements.get(j)) < 0); j++) {
                elements.add(j - 1, elements.get(j));
                elements.remove(j + 1);
            }
        }
    }
}
