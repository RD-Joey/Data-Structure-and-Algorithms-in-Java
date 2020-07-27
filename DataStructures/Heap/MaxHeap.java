package joey.DataStructures.Heap;

public class MaxHeap<T extends Comparable<T>> {
    private T[] heap;
    private int count;

    MaxHeap(int capacity) {
        this.heap = (T[]) new Comparable[capacity + 1];
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean insert(T item) {
        if(this.count == this.heap.length - 1) return false;
        this.heap[++this.count] = item;
        this.swim(this.count);
        return true;
    }

    public T pop() {
        if(count == 0) {
            return null;
        }
        this.swap(1, count);
        this.sink(1);
        return this.heap[count--];
    }

    public T peak() {
        return this.heap[1];
    }

    private void sink(int position) {
        while (position <= this.count >>> 1) {
            if ((position << 1 | 1) <= this.count
                    && this.heap[position << 1].compareTo(this.heap[position << 1 | 1]) <= 0) {
                if (this.heap[position].compareTo(this.heap[position << 1 | 1]) < 0) {
                    this.swap(position, position << 1 | 1);
                    position = position << 1 | 1;
                    continue;
                }
                return;
            }
            if (this.heap[position].compareTo(this.heap[position << 1]) < 0) {
                this.swap(position, position << 1);
                position <<= 1;
                continue;
            }
            return;
        }
    }

    private void swim(int position) {
        while (position > 1 && this.heap[position].compareTo(this.heap[position >>> 1]) > 0) {
            this.swap(position, position >>> 1);
            position >>>= 1;
        }
    }

    private void swap(int i, int j) {
        T temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }
}
