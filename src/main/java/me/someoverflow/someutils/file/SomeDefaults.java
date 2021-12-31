package me.someoverflow.someutils.file;

/**
 * Just a List because I am Stoopid
 *
 * @author SomeOverflow
 */
public class SomeDefaults<E, T> {

    private int size = 0;

    private final Object[] keys;
    private final Object[] values;

    public SomeDefaults() {
        int MAX_SIZE = 100;
        keys = new Object[MAX_SIZE];
        values = new Object[MAX_SIZE];
    }

    public void add(E key, T value) {
        keys[size] = key;
        values[size] = value;
        size++;
    }

    public void addDesc(E key) {
        keys[size] = key;
        values[size] = SomeFile.DESCRIPTION;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E getKey(int index) {
        return (E) keys[index];
    }

    @SuppressWarnings("unchecked")
    public E getValue(int index) {
        return (E) values[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < size ;i++) {
            sb.append(values[i].toString());
            if(i<size-1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public int getSize() {
        return size;
    }
}
