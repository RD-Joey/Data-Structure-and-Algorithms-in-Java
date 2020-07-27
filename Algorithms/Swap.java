package joey.Algorithms;

public class Swap {
    private Swap() {
    }

    @Deprecated
    public static <T> void swap(T a, T b) {
        T temp = a;
        a = b;
        b = temp;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
