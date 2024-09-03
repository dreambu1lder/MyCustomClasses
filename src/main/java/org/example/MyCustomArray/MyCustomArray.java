package org.example.MyCustomArray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Custom implementation of a dynamic array similar to the Java ArrayList.
 *
 * @param <T> the type of elements in this list
 */
public class MyCustomArray<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_LOAD_FACTOR_PERCENT = 75;

    private Object[] array;
    private int size;
    private final int loadFactorPercent;

    /**
     * Constructs an empty list with an initial capacity of ten and a load factor of 75%.
     */
    public MyCustomArray() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_PERCENT);
    }

    /**
     * Constructs a list containing the elements of the specified array, in the order they are returned by the array.
     * The initial capacity of the list will be the size of the array or the default capacity, whichever is larger.
     *
     * @param initialElements the array whose elements are to be placed into this list
     * @throws NullPointerException if the specified array is null
     */
    public MyCustomArray(T[] initialElements) {
        this(Math.max(initialElements.length, DEFAULT_CAPACITY), DEFAULT_LOAD_FACTOR_PERCENT);
        initializeFromArray(initialElements);
    }

    /**
     * Constructs an empty list with the specified initial capacity and load factor.
     *
     * @param initialCapacity the initial capacity of the list
     * @param loadFactorPercent the load factor percentage at which the list's capacity is increased
     * @throws IllegalArgumentException if the specified initial capacity is negative or the load factor is out of bounds
     */
    public MyCustomArray(int initialCapacity, int loadFactorPercent) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Неверная емкость: " + initialCapacity);
        }
        if (loadFactorPercent <= 0 || loadFactorPercent > 100) {
            throw new IllegalArgumentException("Процент загрузки должен быть указан от 1 до 100");
        }
        array = new Object[initialCapacity];
        size = 0;
        this.loadFactorPercent = loadFactorPercent;
    }

    /**
     * Initializes the list by copying elements from the specified array.
     *
     * @param initialElements the array whose elements are to be copied into this list
     * @throws NullPointerException if the specified array is null
     */
    private void initializeFromArray(T[] initialElements) {
        if (initialElements == null) {
            throw new NullPointerException("Массив начальных элементов не может быть пустым");
        }
        System.arraycopy(initialElements, 0, array, 0, initialElements.length);
        size = initialElements.length;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return {@code true} (as specified by Collection.add)
     */
    public boolean add(T element) {
        ensureCapacity(size + 1);
        array[size++] = element;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index > size})
     */
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >= size})
     */
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        T oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
        return oldValue;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >= size})
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >= size})
     */
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        T oldValue = elementData(index);
        array[index] = element;
        return oldValue;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData(i) == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData(i)))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    /**
     * Increases the capacity of this list, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void ensureCapacity(int minCapacity) {
        int loadFactorThreshold = array.length * loadFactorPercent / 100;
        if (minCapacity > loadFactorThreshold) {
            int newCapacity = array.length * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    /**
     * Returns the element at the specified position in this list with proper type casting.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) array[index];
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Sorts the array using the provided comparator.
     *
     * @param comparator the comparator to determine the order of the array
     */
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            T[] elements = Arrays.copyOf(array, size, (Class<T[]>) array.getClass());
            QuickSort.sort(elements, comparator);
            System.arraycopy(elements, 0, array, 0, size);
        }
    }
}
