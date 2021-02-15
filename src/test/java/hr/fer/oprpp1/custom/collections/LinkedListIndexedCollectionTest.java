package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListIndexedCollectionTest {

    @Test
    public void testLinkedListIndexedCollectionDefaultConstructor() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertTrue(result.size() == 0);

    }

    @Test
    public void testLinkedListIndexedCollectionConstructorFromGivenCollection() {

        assertThrows(NullPointerException.class, () -> {
            LinkedListIndexedCollection col = new LinkedListIndexedCollection(null);
        });

        LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        LinkedListIndexedCollection result = new LinkedListIndexedCollection(collection);

        assertArrayEquals(collection.toArray(), result.toArray());

    }


        @Test
        public void testAdd() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        result.add("dva");
        assertEquals(1, result.size());
        assertTrue(result.get(0).equals("dva"));
        assertThrows(NullPointerException.class, () -> result.add(null));
    }

    @Test
    public void testForEach() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        result.add(1);
        result.add(3);
        result.add(-2);
        result.forEach(new Processor() {

            @Override
            public void process(Object value) {
                System.out.println(value);
            }
        });
    }

    @Test
    public void testClear() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        result.add(1);
        result.add(3);
        result.add(-2);
        result.clear();
        assertArrayEquals(new Object[] {}, result.toArray());
    }

    @Test
    public void testGet() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertThrows(IndexOutOfBoundsException.class, () -> result.get(0));
        result.add(1);
        result.add(3);
        result.add(-2);
        assertEquals(3, result.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> result.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> result.get(-1));

    }

    @Test
    public void testInsert() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertThrows(NullPointerException.class, () -> result.insert(null, 0));
        result.insert(5, result.size());
        assertEquals(5, result.get(0));
        result.add(1);
        result.add(3);
        result.add(-2);
        result.insert(4, 1);
        assertArrayEquals(new Object[] {5,4,1,3,-2}, result.toArray());
        assertThrows(IndexOutOfBoundsException.class, () -> result.insert(5, -5));
        assertThrows(IndexOutOfBoundsException.class, () -> result.insert(5,6));

    }

    @Test
    public void testIndexOf() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        result.add("sedam");
        result.add("osam");
        result.add("pet");
        result.add("pet");
        assertEquals(1, result.indexOf("osam"));
        assertEquals(2, result.indexOf("pet"));
    }

    @Test
    public void testRemove() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertThrows(IndexOutOfBoundsException.class, () -> result.remove(0));
        result.add("pet");
        result.remove(0);
        assertArrayEquals(new Object[] {}, result.toArray());
        result.add("pet");
        result.add("tri");
        result.add("pet");
        result.remove(1);
        assertArrayEquals(new Object[] {"pet", "pet"}, result.toArray());
        assertThrows(IndexOutOfBoundsException.class, () -> result.remove(10));
    }

    @Test
    public void testSize() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertEquals(0, result.size());
        result.add("tri");
        result.add("tri");
        result.add("tri");
        result.add("tri");
        result.add("tri");
        assertEquals(5, result.size());
    }

    @Test
    public void testContains() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertFalse(result.contains("pet"));
        result.add("pet");
        assertTrue(result.contains("pet"));
        result.add("osam");
        result.add("tri");
        result.add("osam");
        result.add("sedam");
        assertTrue(result.contains("tri"));
        assertTrue(result.contains("osam"));
        assertTrue(result.contains("sedam"));
        assertFalse(result.contains("devet"));

    }

    @Test
    public void testToArray() {
        LinkedListIndexedCollection result = new LinkedListIndexedCollection();
        assertArrayEquals(new Object[] {}, result.toArray());
        result.add(1);
        result.add(2);
        result.add(3);
        assertArrayEquals(new Object[] {1,2,3}, result.toArray());
    }
}