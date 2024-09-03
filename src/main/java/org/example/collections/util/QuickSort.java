package org.example.collections.util;

import java.util.Comparator;

/**
 * Utility class that provides a static method to sort an array using the QuickSort algorithm.
 * This class is designed to work with any type of elements that can be compared using a {@link Comparator}.
 */
public class QuickSort {

    /**
     * Sorts the specified array into ascending order, according to the order induced by the specified comparator.
     * This method uses the QuickSort algorithm, which is an efficient, in-place, divide-and-conquer sorting algorithm.
     *
     * @param <T> the type of elements in the array
     * @param array the array to be sorted
     * @param comparator the comparator to determine the order of the array. A null comparator indicates that
     * the natural ordering of the elements should be used.
     * @return the sorted array
     */
    public static <T> T[] sort(T[] array, Comparator<T> comparator) {
        return recQuickSort(0, array.length-1, comparator, array);
    }

    /**
     * Recursively sorts the array using the QuickSort algorithm.
     * This method is called by the public {@link #sort(Object[], Comparator)} method.
     *
     * @param <T> the type of elements in the array
     * @param left the leftmost index of the sub-array to be sorted
     * @param right the rightmost index of the sub-array to be sorted
     * @param comparator the comparator to determine the order of the array
     * @param array the array to be sorted
     * @return the sorted sub-array
     */
    private static <T> T[]  recQuickSort(int left, int right, Comparator<T> comparator, T[] array) {
        int size = right - left + 1;
        if (size <= 3) {
            return manualSort(left, right, comparator, array);
        } else {
            int median = medianOf3(left, right, array, comparator);
            int partition = partitionIt(left, right, median, comparator, array);
            recQuickSort(left, partition - 1, comparator, array);
            recQuickSort(partition + 1, right, comparator, array);
        }
        return array;
    }

    /**
     * Finds the median of three elements and rearranges them in the array so that the median is used as the pivot.
     *
     * @param <T> the type of elements in the array
     * @param left the leftmost index of the sub-array
     * @param right the rightmost index of the sub-array
     * @param array the array to be sorted
     * @param comparator the comparator to determine the order of the array
     * @return the index of the pivot element
     */
    private static <T> int medianOf3(int left, int right, T[] array, Comparator<T> comparator) {
        int center = left + (right - left) / 2;
        if (comparator.compare(array[left], array[center]) > 0) {
            swap(left, center, array);
        }
        if (comparator.compare(array[left], array[right]) > 0) {
            swap(left, right, array);
        }
        if (comparator.compare(array[center], array[right]) > 0){
            swap(center, right, array);
        }
        swap(center, right-1, array);
        return right-1;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param <T> the type of elements in the array
     * @param left the index of the first element to be swapped
     * @param right the index of the second element to be swapped
     * @param array the array containing the elements to be swapped
     */
    private static <T> void swap(int left, int right, T[] array) {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * Partitions the array around the pivot element.
     *
     * @param <T> the type of elements in the array
     * @param left the leftmost index of the sub-array to be partitioned
     * @param right the rightmost index of the sub-array to be partitioned
     * @param pivot the index of the pivot element
     * @param comparator the comparator to determine the order of the array
     * @param array the array to be partitioned
     * @return the index of the partition point
     */
    private static <T> int partitionIt(int left, int right, int pivot, Comparator<T> comparator, T[] array) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while(true) {
            while (comparator.compare(array[++leftPtr], array[pivot]) < 0);
            while (comparator.compare(array[--rightPtr], array[pivot]) > 0);
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr, array);
            }
        }
        swap(leftPtr, right - 1,  array);
        return leftPtr;
    }

    /**
     * Manually sorts small sub-arrays of three or fewer elements.
     * This method is used as a fallback in the QuickSort algorithm for small sub-arrays.
     *
     * @param <T> the type of elements in the array
     * @param left the leftmost index of the sub-array to be sorted
     * @param right the rightmost index of the sub-array to be sorted
     * @param comparator the comparator to determine the order of the array
     * @param array the array to be sorted
     * @return the sorted sub-array
     */
    private static <T> T[] manualSort(int left, int right, Comparator<T> comparator, T[] array) {
        int size = right - left + 1;
        if (size <= 1) {
            return array;
        }
        if (size == 2) {
            if(comparator.compare(array[left], array[right]) > 0) {
                swap(left, right, array);
            }
        } else {
            if(comparator.compare(array[left], array[right-1]) > 0) {
                swap(left, right-1, array);
            }
            if(comparator.compare(array[left], array[right]) > 0) {
                swap(left, right, array);
            }
            if (comparator.compare(array[right-1], array[right]) > 0) {
                swap(right-1, right, array);
            }
        }
        return array;
    }

}
