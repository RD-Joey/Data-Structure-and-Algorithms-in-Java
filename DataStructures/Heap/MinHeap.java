package joey.DataStructures.Heap;

public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int count;

    public MinHeap(int capacity) {
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
        while (position <= this.count >>> 1) {  // 进了循环表示左子节点必存在
            if ((position << 1 | 1) <= this.count   // 判断右节点是否存在
                    // 执行到这右子节点必存在，下面判断考虑哪个节点（左右子节点比大小）
                    && this.heap[position << 1].compareTo(this.heap[position << 1 | 1]) >= 0) {
                // 执行到这已无需考虑左子节点，判断是否 sink（与右子节点比大小）
                if (this.heap[position].compareTo(this.heap[position << 1 | 1]) > 0) {
                    this.swap(position, position << 1 | 1);
                    position = position << 1 | 1;
                    continue;
                }
                return;
            }
            // 执行到这已无需考虑右子节点，判断是否 sink（与左子节点比大小）
            if (this.heap[position].compareTo(this.heap[position << 1]) > 0) {
                this.swap(position, position << 1);
                position <<= 1;
                continue;
            }
            // sink 完成，直接返回
            return;
            // 这里每次 sink 都是进行交换，可以参考插入排序进行优化
            // 先将原节点取出，进行比较，直到找到合适位置再放回堆
        }
    }

    private void swim(int position) {
        while (position > 1 && this.heap[position].compareTo(this.heap[position >>> 1]) < 0) {
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
