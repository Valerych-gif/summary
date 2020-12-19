package lesson2;

public class LinkedListDemo<E> implements ListDemo<E>{
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E o) {
        return false;
    }

    @Override
    public boolean add(E o) {
        return false;
    }

    @Override
    public boolean remove(E o) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }
}
