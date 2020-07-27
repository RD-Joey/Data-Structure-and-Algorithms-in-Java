package joey.Algorithms.Sort;

import joey.Algorithms.Swap;
import org.jetbrains.annotations.NotNull;

public class QuickSort {
    private QuickSort() {
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort(@NotNull T[] arr, int l, int r) {
        if (r - l < 30) {   // 递归底优化，你懂的
            InsertionSort.sort(arr, l, r);
        }
        // 随机取 pivot，放到数组首元素的位置（可以使用三，五，十数取中进一步优化）
        Swap.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        // less than->lt    greater than->gt    i 当前处理元素
        int lt = l, i = l + 1, gt = r + 1;
        // arr(l...lt] < pivot;  arr(lt...i) = pivot;   arr[gt...r] > pivot
        while (i < gt) {    // i == gt 时已无未遍历元素
            // 注意上面已将 pivot 放到数组头，因此只需与数组下标为 l 的元素进行比较
            if (arr[i].compareTo(arr[l]) < 0) { // 比 pivot 小，放到第一个区间
                Swap.swap(arr, ++lt, i++);  // 注意维护区间下标，并处理下一个元素
            } else if (arr[i].compareTo(arr[l]) > 0) {  // 比 pivot 大，放到第三个区间
                Swap.swap(arr, i, --gt);    // 注意维护区间下标，注意此处 i 不能递增
            } else {    // 与 pivot 相等，留在第二区间
                i++;
            }
        }
        Swap.swap(arr, l, lt);  // 将 pivot 放回第一区间的最后一个位置
        sort(arr, l, lt - 1);   // 注意上一行代码并未维护 lt 下标，因此需传入 lt - 1
        sort(arr, gt, r);
    }
}
