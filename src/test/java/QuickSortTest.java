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

        assertArrayEquals(expected, array, "Массив должен быть отсортирован в порядке возрастания.");
    }

    @Test
    void testSortIntegersReverseOrder() {
        Integer[] array = {5, 3, 8, 1, 2};
        Integer[] expected = {8, 5, 3, 2, 1};

        QuickSort.sort(array, Comparator.reverseOrder());

        assertArrayEquals(expected, array, "Массив должен быть отсортирован в порядке убывания.");
    }

    @Test
    void testSortStringsNaturalOrder() {
        String[] array = {"banana", "apple", "orange", "kiwi"};
        String[] expected = {"apple", "banana", "kiwi", "orange"};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "Массив должен быть отсортирован в алфавитном порядке.");
    }

    @Test
    void testSortStringsReverseOrder() {
        String[] array = {"banana", "apple", "orange", "kiwi"};
        String[] expected = {"orange", "kiwi", "banana", "apple"};

        QuickSort.sort(array, Comparator.reverseOrder());

        assertArrayEquals(expected, array, "Массив должен быть отсортирован в обратном алфавитном порядке.");
    }

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        Integer[] expected = {};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "Пустой массив должен оставаться пустым после сортировки.");
    }

    @Test
    void testSortSingleElementArray() {
        Integer[] array = {42};
        Integer[] expected = {42};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "Массив с одним элементом должен остаться без изменений после сортировки.");
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "Уже отсортированный массив должен остаться без изменений.");
    }

    @Test
    void testSortArrayWithDuplicates() {
        Integer[] array = {5, 3, 8, 1, 2, 3, 5};
        Integer[] expected = {1, 2, 3, 3, 5, 5, 8};

        QuickSort.sort(array, Comparator.naturalOrder());

        assertArrayEquals(expected, array, "Массив с дубликатами должен быть отсортирован правильно.");
    }
}
