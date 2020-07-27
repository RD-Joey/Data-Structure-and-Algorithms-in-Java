package joey.Algorithms.Sort;

import joey.Algorithms.Swap;
import org.jetbrains.annotations.NotNull;

public class SelectionSort {
    private SelectionSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r) {
        if (r - l < 1) {
            return;
        }
        for (int i = l, minIndex = l; i < r; i++, minIndex++) {
            for (int j = i + 1; j <= r; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
                if (i != minIndex) {
                    Swap.swap(arr, i, minIndex);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
