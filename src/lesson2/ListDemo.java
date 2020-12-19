package lesson2;

public interface ListDemo <E> {
    int size();

    boolean isEmpty();

    boolean contains(E entity);

    boolean add(E entity);

    boolean remove(E entity);

    E get(int index);

    E set(int index, E entity);
}
