package joey.Algorithms.Sort;

import joey.Algorithms.Swap;
import org.jetbrains.annotations.NotNull;

public class HeapSort {
    private HeapSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r) {
        if (r - l < 1) {
            return;
        }
        int offset = l - 1, heapSize = r - l + 1;
        for(int i = (heapSize >>> 1); i > 0; i--) {
            sink(arr, i, heapSize, offset);
        }
        while (r > l) {
            Swap.swap(arr, l, r--);
            sink(arr, l - offset, heapSize--, offset);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] heap, int pivot, int heapSize, int offset) {
        if (heapSize < 2) {
            return;
        }
        while (pivot <= (heapSize >>> 1)) {
            if ((pivot << 1 | 1) <= heapSize
                    && heap[(pivot << 1) + offset].compareTo(heap[(pivot << 1 | 1) +  offset]) <= 0) {
                if (heap[pivot + offset].compareTo(heap[(pivot << 1 | 1) + offset]) < 0) {
                    Swap.swap(heap, pivot + offset, (pivot << 1 | 1) + offset);
                    pivot = pivot << 1 | 1;
                    continue;
                }
                return;
            }
            if (heap[pivot + offset].compareTo(heap[(pivot << 1) + offset]) < 0) {
                Swap.swap(heap, pivot + offset, (pivot << 1) + offset);
                pivot <<= 1;
                continue;
            }
            return;
        }
    }
}
