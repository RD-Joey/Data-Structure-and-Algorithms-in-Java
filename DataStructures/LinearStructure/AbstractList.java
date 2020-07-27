package joey.DataStructures.LinearStructure;

public abstract class AbstractList<E> implements List<E> {
    public abstract void add(int index, E element);

    public abstract boolean add(E e);

    public abstract void clear();

    public abstract boolean contains(E target);

    public abstract E get(int index);

    public abstract int indexOf(E e);

    public abstract boolean isEmpty();

    public abstract int lastIndexOf(E e);

    public abstract E remove(int index);

    public abstract boolean remove(E e);

    public abstract E set(int index, E element);

    public abstract int size();

    public abstract List<E> subList(int fromIndex, int toIndex);

    public abstract Object[] toArray();
}
