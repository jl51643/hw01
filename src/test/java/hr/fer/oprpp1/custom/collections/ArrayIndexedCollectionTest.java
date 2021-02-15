package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayIndexedCollectionTest {

    @Test
    public void testArrayIndexedCollectionDefaultConstructor() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        assertArrayEquals(new Object[]{}, result.toArray());
        assertEquals(0, result.size());
    }

    @Test
    public void testArrayIndexedCollectionInitialCapacityConstructor() {
        ArrayIndexedCollection result = new ArrayIndexedCollection(10);
        assertArrayEquals(new Object[]{}, result.toArray());
        assertEquals(0, result.size());
    }

    @Test
    public void testArrayIndexedCollectionGivenCollectionConstructor() {
        ArrayIndexedCollection collection = new ArrayIndexedCollection();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        ArrayIndexedCollection result = new ArrayIndexedCollection(collection);
        assertArrayEquals(new Object[] {1, 2, 3}, result.toArray());
    }

    @Test
    public void testArrayIndexedCollectionGivenCollectionConstructorAndInitialCapacityConstructor() {
        ArrayIndexedCollection collection = new ArrayIndexedCollection();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        ArrayIndexedCollection result = new ArrayIndexedCollection(collection, 2);
        assertArrayEquals(new Object[] {1, 2, 3}, result.toArray());
    }

    @Test
    public void testSize() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add("a");
        result.add("b");
        assertTrue(result.size() == 2);
        result.add("c");
        assertTrue(result.size() == 3);
        result.remove(0);
        assertTrue(result.size() == 2);
    }

    @Test
    public void testAdd() {
        assertThrows(NullPointerException.class, () -> {
            ArrayIndexedCollection res = new ArrayIndexedCollection();
            res.add(null);
        });
        ArrayIndexedCollection result = new ArrayIndexedCollection(2);
        result.add(1);
        result.add(2);
        result.add(3);
        assertTrue(result.size() == 3);

    }

    @Test
    public void testForEach() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add(1);
        result.add(2);
        result.forEach(new Processor() {
            @Override
            public void process(Object value) {
                int index = result.indexOf(value);
                result.remove(index);
                result.insert("tri", index);
            }
        });
        assertArrayEquals(new Object[] {"tri", "tri"}, result.toArray());
    }

    @Test
    public void testClear() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add(1);
        result.add(2);
        result.add(3);
        assertTrue(result.size() == 3);
        result.clear();
        assertTrue(result.size() == 0);
    }

    @Test
    public void testContains() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add("dva");
        assertTrue(result.contains("dva"));
        assertFalse(result.contains("tri"));
        assertFalse(result.contains(null));
    }

    @Test
    public void testToArray() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add(1);
        result.add(2);
        result.add(3);
        assertArrayEquals(new Object[]{1, 2, 3}, result.toArray());
        result.add("tri");
        assertArrayEquals(new Object[] {1, 2, 3, "tri"}, result.toArray());
    }

    @Test
    public void testGet() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        result.add("pet");
        result.add("7");
        assertEquals("pet", result.get(0));
        assertEquals("7", result.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> result.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> result.get(result.size()));

    }

    @Test
    public void testInsert() {
        ArrayIndexedCollection result = new ArrayIndexedCollection(1);
        result.insert("tri", 0);
        assertArrayEquals(new Object[] {"tri"}, result.toArray());
        result.insert("dva", 0);
        assertArrayEquals(new Object[] {"dva", "tri"}, result.toArray());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            result.insert("pet", 4);
        });
        assertThrows(NullPointerException.class, () -> {
            result.insert(null, -5);
        });
        result.insert("dva", 1);
        assertArrayEquals(new Object[] {"dva", "dva", "tri"}, result.toArray());
    }

    @Test
    public void testIndexOf() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        assertTrue(-1 == result.indexOf("sedam"));
        result.add("sedam");
        assertTrue(0 == result.indexOf("sedam"));
        result.add("tri");
        result.remove(0);
        assertTrue(-1 == result.indexOf("sedam"));
        assertTrue(-1 == result.indexOf(null));
    }

    @Test
    public void testRemove() {
        ArrayIndexedCollection result = new ArrayIndexedCollection();
        assertThrows(IndexOutOfBoundsException.class, () -> result.remove(5));
        result.add("pet");
        result.add("tri");
        result.add("sedam");
        result.add(6);
        result.remove(2);
        assertArrayEquals(new Object[]{"pet", "tri", 6}, result.toArray());

    }
}
