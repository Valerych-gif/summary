package lesson2;


public class ArrayListDemo<E extends Comparable<? super E>> implements ListDemo<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;

    public ArrayListDemo() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListDemo(int capacity) {
        this.data = (E[])new Comparable[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E entity) {
        for (E d : data) {
            if (entity.equals(d))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(E entity) {
        if (data.length==size){
            E[] tmp = (E[])new Comparable[size*2];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;
        }
        data[size] = entity;
        size++;
        return true;
    }

    @Override
    public boolean remove(E entity) {
        for (int i = 0; i < size; i++) {
            if (entity.equals(data[i])){
                System.arraycopy(data, i + 1, data, i, size - i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public E set(int index, E entity) {
        if (index<size) {
            data[index] = entity;
            return entity;
        }
        return null;
    }
}
