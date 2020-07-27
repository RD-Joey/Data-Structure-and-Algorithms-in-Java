package joey.Algorithms.Sort;

import org.jetbrains.annotations.NotNull;

public class InsertionSort {
    private InsertionSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r) {
        if (r - l < 1) {
            return;
        }
        for (int i = 1; i <= r; i++) {
            T temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j].compareTo(temp) > 0) {
                    arr[j + 1] = arr[j];
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
