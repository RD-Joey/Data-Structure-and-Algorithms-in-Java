package joey.Algorithms;

import org.jetbrains.annotations.NotNull;

public class BinarySearch {
    private BinarySearch() {
    }

    public static <T extends Comparable<T>> int binarySearch(@NotNull T[] sortedArray, T target) {
        int l = 0, r = sortedArray.length - 1, mid;
        while (l <= r) {
            mid = l + (r - l >>> 1);
            if (sortedArray[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else if (sortedArray[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
