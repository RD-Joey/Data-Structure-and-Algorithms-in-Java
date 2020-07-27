package joey.Algorithms.Sort;

import org.jetbrains.annotations.NotNull;

public class MergeSort {
    private MergeSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r){
        if (r - l < 30) {
            InsertionSort.sort(arr, l, r);
        }
        int mid = l + (r - l >>> 1);
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    public static <T extends Comparable<T>> void mergeSortBU(@NotNull T[] arr) {
        mergeSortBU(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void mergeSortBU(@NotNull T[] arr, int l, int r) {
        int length = r - l + 1;
        if (length < 2) {
            return;
        }
        for (int size = 1; size < length; size <<= 1) {
            for (int i = 0; i + size < length; i += (size << 1)) {
                int bound = Math.min(i + (size << 1) - 1, length);
                if (size <= 8) {
                    InsertionSort.sort(arr, i, bound);
                } else if (arr[i + size - 1].compareTo(arr[i + size]) > 0) {
                    merge(arr, i, i + size - 1, bound);
                }
            }
        }
    }

    private static void merge(Comparable[] array, int l, int mid, int r) {
        Comparable[] copy = new Comparable[r - l + 1];
        if (copy.length >= 0) System.arraycopy(array, l, copy, 0, copy.length);
        mid -= l;   // 将 mid 重定位到新数组下标
        r = mid + 1;    // i, r 分别为新数组左右子数组当前元素的下标
        int i = 0;
        while (i <= mid && r < copy.length) {   // 其中一个子数组拷贝完毕时结束循环
            // l 为原数组当前元素的下标
            if (copy[i].compareTo(copy[r]) <= 0) {
                array[l++] = copy[i++];
            } else {
                array[l++] = copy[r++];
            }
        }
        if (i > mid) {  // 判断哪个子数组未拷贝完成，并完成拷贝
            System.arraycopy(copy, r, array, l, copy.length - r);
        } else {
            System.arraycopy(copy, i, array, l, mid - i + 1);
        }
        // 1. 可以利用堆进行多路归并优化，当子数组个数达到数组长度时退化为堆排序
        // 2. 可以参考源码双路归并的交叉拷贝进行优化
    }
}
