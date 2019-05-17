package sort;

import java.util.ArrayList;

public class SortingImpl<T extends Comparable> implements Sorting<T> {

    /**
     * Sorts the elements in ascending or descending order
     * <p>
     * {@code SortingType.DESCENDING} - descending
     * <p>
     * {@code SortingType.ASCENDING} - ascending
     *
     * @param elements Items to sort
     */
    @Override
    public void sort(ArrayList<T> elements, SortingType sortingType) {
        if (sortingType.equals(SortingType.ASCENDING)) {
            sortAscending(elements);
        } else {
            sortDescending(elements);
        }
    }

    private void sortAscending(ArrayList<T> elements) {
        for (int i = 1; i < elements.size(); i++) {
            T key = elements.get(i);
            for (int j = i - 1; j >= 0 && (key.compareTo(elements.get(j)) < 0); j--) {
                elements.add(j, elements.get(j + 1));
                elements.remove(j + 2);
            }
        }
    }

    private void sortDescending(ArrayList<T> elements) {
        for (int i = elements.size() - 2; i >= 0; i--) {
            T key = elements.get(i);
            for (int j = i + 1; j < elements.size() && (key.compareTo(elements.get(j)) < 0); j++) {
                elements.add(j - 1, elements.get(j));
                elements.remove(j + 1);
            }
        }
    }
}
