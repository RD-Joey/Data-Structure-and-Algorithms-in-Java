package joey.DataStructures.LinearStructure;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<E> extends AbstractList<E> implements List<E> {
    private static final Object[] EMPTY_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = EMPTY_DATA;
    }

    public ArrayList(Object[] data) {
        this.data = data;
        this.size = data.length;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = EMPTY_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(" Index: " + index + ", Size: " + this.size);
        }
        if (this.size == this.data.length) {
            this.grow();
        }
        System.arraycopy(this.data, index, this.data, index + 1,  this.size - index);
        this.data[index] = element;
        this.size++;
    }

    @Override
    public boolean add(E e) {
        this.add(this.size, e);
        return true;
    }

    @Override
    public void clear() {
        while (this.size > 0) {
            this.data[--size] = null;
        }
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) > -1;
    }

    @Override
    public E get(int index) {
        return (E)this.data[index];
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.data == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (e.equals(this.data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int lastIndexOf(E e) {
        if (e == null) {
            for (int i = this.size - 1; i >= 0; i--) {
                if (this.data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = this.size - 1; i >= 0; i--) {
                if (e.equals(this.data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, this.size);
        @SuppressWarnings("unchecked") E ret = (E) this.data[index];
        if (--this.size > 1) {
            System.arraycopy(this.data, index + 1, this.data, index, size - index);
        }
        this.data[this.size] = null;
        return ret;
    }

    @Override
    public boolean remove(E e) {
        int index;
        if ((index = this.indexOf(e)) == -1) {
            return false;
        }
        this.remove(index);
        return true;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    private void grow() {
        this.data = Arrays.copyOf(this.data, Math.max(this.data.length * 2, DEFAULT_CAPACITY));
    }
}
