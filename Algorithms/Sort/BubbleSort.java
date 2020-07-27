package joey.Algorithms.Sort;

import joey.Algorithms.Swap;
import org.jetbrains.annotations.NotNull;

public class BubbleSort {
    private BubbleSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r) {
        if (l - r < 1) {
            return;
        }
        boolean flag;
        for (int i = r; i > l; i--) {
            flag = true;
            for (int j = l; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    flag = false;
                    Swap.swap(arr, j, j + 1);
                }
            }
            if (flag) {
                return;
            }
        }
    }
}
