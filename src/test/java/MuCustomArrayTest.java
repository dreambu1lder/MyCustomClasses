import org.example.MyCustomArray.MyCustomArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class MuCustomArrayTest {

    private MyCustomArray<Integer> myCustomArray;

    @BeforeEach
    void setUp() {
        myCustomArray = new MyCustomArray<>();
    }

    @Test
    void testAdd() {
        assertTrue(myCustomArray.add(10));
        assertEquals(1, myCustomArray.size());
        assertEquals(10, myCustomArray.get(0));
    }

    @Test
    void testAddAtIndex() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(1, 5);

        assertEquals(3, myCustomArray.size());
        assertEquals(0, myCustomArray.get(0));
        assertEquals(5, myCustomArray.get(1));
        assertEquals(1, myCustomArray.get(2));
    }

    @Test
    void testRemove() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        int removed = myCustomArray.remove(1);
        assertEquals(1, removed);
        assertEquals(2, myCustomArray.size());
        assertEquals(2, myCustomArray.get(1));
    }

    @Test
    void testGet() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        assertEquals(0, myCustomArray.get(0));
        assertEquals(1, myCustomArray.get(1));
        assertEquals(2, myCustomArray.get(2));
    }

    @Test
    void testSet() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        int oldValue = myCustomArray.set(1, 5);
        assertEquals(1, oldValue);
        assertEquals(5, myCustomArray.get(1));
    }

    @Test
    void testContains() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        assertTrue(myCustomArray.contains(1));
        assertFalse(myCustomArray.contains(3));
    }

    @Test
    void testIndexOf() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        assertEquals(1, myCustomArray.indexOf(1));
        assertEquals(-1, myCustomArray.indexOf(3));
    }

    @Test
    void testSize() {
        assertEquals(0, myCustomArray.size());
        myCustomArray.add(0);
        assertEquals(1, myCustomArray.size());
    }

    @Test
    void testClear() {
        myCustomArray.add(0);
        myCustomArray.add(1);
        myCustomArray.add(2);

        myCustomArray.clear();
        assertEquals(0, myCustomArray.size());
        assertTrue(myCustomArray.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(myCustomArray.isEmpty());
        myCustomArray.add(0);
        assertFalse(myCustomArray.isEmpty());
    }

    @Test
    void testConstructorWithInitialElements() {
        Integer[] initialElements = {1, 2, 3};
        MyCustomArray<Integer> myArray = new MyCustomArray<>(initialElements);

        assertEquals(3, myArray.size());
        assertEquals(1, myArray.get(0));
        assertEquals(2, myArray.get(1));
        assertEquals(3, myArray.get(2));
    }

    @Test
    void testConstructorWithInvalidCapacity() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MyCustomArray<>(-1, 50));
        assertEquals("Неверная емкость: -1", exception.getMessage());
    }

    @Test
    void testConstructorWithInvalidLoadFactor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MyCustomArray<>(10, 150));
        assertEquals("Процент загрузки должен быть указан от 1 до 100", exception.getMessage());
    }

    @Test
    void testEnsureCapacity() {
        for (int i = 0; i < 20; i++) {
            myCustomArray.add(i);
        }
        assertEquals(20, myCustomArray.size());
        assertEquals(19, myCustomArray.get(19));
    }

    @Test
    void testSortWithComparator() {
        MyCustomArray<Integer> myArray = new MyCustomArray<>();
        myArray.add(5);
        myArray.add(3);
        myArray.add(8);
        myArray.add(1);

        Integer[] expected = {1, 3, 5, 8};

        myArray.sort(Comparator.naturalOrder());

        assertArrayEquals(expected, myArray.toArray(), "Массив должен быть отсортирован в порядке возрастания.");
    }

    @Test
    void testSortWithReverseOrderComparator() {
        MyCustomArray<Integer> myArray = new MyCustomArray<>();
        myArray.add(5);
        myArray.add(3);
        myArray.add(8);
        myArray.add(1);

        Integer[] expected = {8, 5, 3, 1};

        myArray.sort(Comparator.reverseOrder());

        assertArrayEquals(expected, myArray.toArray(), "Массив должен быть отсортирован в порядке убывания.");
    }

    @Test
    void testSortEmptyArray() {
        MyCustomArray<Integer> myArray = new MyCustomArray<>();

        Integer[] expected = {};

        myArray.sort(Comparator.naturalOrder());

        assertArrayEquals(expected, myArray.toArray(), "Пустой массив должен остаться без изменений.");
    }

    @Test
    void testSortSingleElementArray() {
        MyCustomArray<Integer> myArray = new MyCustomArray<>();
        myArray.add(10);

        Integer[] expected = {10};

        myArray.sort(Comparator.naturalOrder());

        assertArrayEquals(expected, myArray.toArray(), "Массив с одним элементом должен остаться без изменений.");
    }
}
