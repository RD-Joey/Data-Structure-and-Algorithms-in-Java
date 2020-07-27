package joey.DataStructures.LinearStructure;

public interface List<E> {
    public void add(int index, E element);

    public boolean add(E e);

    public void clear();

    public boolean contains(E target);

    public E get(int index);

    public int indexOf(E e);

    public boolean isEmpty();

    public int lastIndexOf(E e);

    public E remove(int index);

    public boolean remove(E e);

    public E set(int index, E element);

    public int size();

    List<E> subList(int fromIndex, int toIndex);

    public Object[] toArray();
}
