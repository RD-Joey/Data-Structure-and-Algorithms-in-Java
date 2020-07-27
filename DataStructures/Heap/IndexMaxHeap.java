package joey.DataStructures.Heap;

public class IndexMaxHeap<T extends Comparable<T>> {
    private T[] data;
    private int[] indexHeap;
    private int[] reverse;
    private int count;

    IndexMaxHeap(int capacity) {
        this.data = (T[]) new Comparable[capacity + 1];
        this.indexHeap = new int[capacity + 1];
        this.reverse = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            this.reverse[i] = 0;
        }
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean insert(int index, T item) {
        if (this.count == this.indexHeap.length - 1
                || (index < 0 && index > this.indexHeap.length - 2)) {
            return false;
        }
        if (contains(index)) {
            return this.update(index, item);
        }
        this.data[++index] = item;
        this.indexHeap[++this.count] = index;
        this.reverse[index] = this.count;
        this.swim(this.count);
        return true;
    }

    public T extractMax() {
        if (this.count == 0) {
            return null;
        }
        this.swap(1, count);
        this.reverse[this.indexHeap[1]] = 1;
        this.reverse[this.indexHeap[count]] = 0;
        this.sink(1);
        return this.data[this.indexHeap[count--]];
    }

    public T peak() {
        return this.data[this.indexHeap[1]];
    }

    public int extractMaxIndex() {
        if (this.count == 0) {
            return -1;
        }
        this.swap(1, count);
        this.reverse[this.indexHeap[1]] = 1;
        this.reverse[this.indexHeap[count]] = 0;
        this.sink(1);
        return this.indexHeap[count--] - 1;
    }

    public boolean contains(int index) {
        if (index < 0 && index > this.indexHeap.length - 2) {
            return false;
        }
        return this.reverse[index + 1] != 0;
    }

    public T getItem(int index) {
        if (!this.contains(index)) {
            return null;
        }
        return this.data[index + 1];
    }

    public boolean update(int index, T newItem) {
        if (!this.contains(index)) {
            return false;
        }
        this.data[++index] = newItem;
        int position = this.reverse[index];
        this.swim(position);
        if (this.indexHeap[position] == index) {
            this.sink(position);
        }
        return true;
    }

    private void sink(int position) {
        while (position <= this.count >>> 1) {
            if ((position << 1 | 1) <= this.count
                    && this.data[this.indexHeap[position << 1]].compareTo(this.data[this.indexHeap[position << 1 | 1]]) <= 0) {
                if (this.data[this.indexHeap[position]].compareTo(this.data[this.indexHeap[position << 1 | 1]]) < 0) {
                    this.swap(position, position << 1 | 1);
                    this.reverse[this.indexHeap[position]] = position;
                    position = position << 1 | 1;
                    this.reverse[this.indexHeap[position]] = position;
                    continue;
                }
                return;
            }
            if (this.data[this.indexHeap[position]].compareTo(this.data[this.indexHeap[position << 1]]) < 0) {
                this.swap(position, position << 1);
                this.reverse[this.indexHeap[position]] = position;
                position <<= 1;
                this.reverse[this.indexHeap[position]] = position;
                continue;
            }
            return;
        }
    }

    private void swim(int position) {
        while (position > 1
                && this.data[this.indexHeap[position]].compareTo(this.data[this.indexHeap[position >>> 1]]) > 0) {
            this.swap(position, position >>> 1);
            this.reverse[this.indexHeap[position]] = position;
            position >>>= 1;
            this.reverse[this.indexHeap[position]] = position;
        }
    }

    private void swap(int i, int j) {
        int temp = this.indexHeap[i];
        this.indexHeap[i] = this.indexHeap[j];
        this.indexHeap[j] = temp;
    }
}
