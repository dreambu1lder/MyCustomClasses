import org.example.MyCustomArray.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testSortIntegersNaturalOrder() {
        Integer[] array = {5, 3, 8, 1, 2};
        Integer[] expected = {1, 2, 3, 5, 8};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "The array should be sorted in ascending order.");
    }

    @Test
    void testSortIntegersReverseOrder() {
        Integer[] array = {5, 3, 8, 1, 2};
        Integer[] expected = {8, 5, 3, 2, 1};

        QuickSort.sort(array, Comparator.reverseOrder());

        assertArrayEquals(expected, array, "The array should be sorted in descending order.");
    }

    @Test
    void testSortStringsNaturalOrder() {
        String[] array = {"banana", "apple", "orange", "kiwi"};
        String[] expected = {"apple", "banana", "kiwi", "orange"};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "The array should be sorted alphabetically.");
    }

    @Test
    void testSortStringsReverseOrder() {
        String[] array = {"banana", "apple", "orange", "kiwi"};
        String[] expected = {"orange", "kiwi", "banana", "apple"};

        QuickSort.sort(array, Comparator.reverseOrder());

        assertArrayEquals(expected, array, "The array should be sorted in reverse alphabetical order.");
    }

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        Integer[] expected = {};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "An empty array should remain empty after sorting.");
    }

    @Test
    void testSortSingleElementArray() {
        Integer[] array = {42};
        Integer[] expected = {42};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "A single-element array should remain unchanged after sorting.");
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "An already sorted array should remain unchanged.");
    }

    @Test
    void testSortArrayWithDuplicates() {
        Integer[] array = {5, 3, 8, 1, 2, 3, 5};
        Integer[] expected = {1, 2, 3, 3, 5, 5, 8};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "The array with duplicates should be sorted correctly.");
    }
}
