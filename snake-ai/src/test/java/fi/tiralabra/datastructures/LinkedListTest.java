package fi.tiralabra.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 *
 * @author Vili Lipo
 */
public class LinkedListTest {

    LinkedList<String> list;
    String first;
    String third;
    String second;

    public LinkedListTest() {
    }
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        list = new LinkedList<String>();
        first = "First";
        third = "Third";
        second = "Second";
        list.add(first);
        list.add(second);
        list.add(third);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIterator() {
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("First", it.next());
        assertTrue(it.hasNext());
        assertEquals("Second", it.next());
        assertTrue(it.hasNext());
        assertEquals("Third", it.next());
        assertFalse(it.hasNext());
        exception.expect(NoSuchElementException.class);
        it.next();
    }

    @Test
    public void testGet() {
        assertEquals("First", list.get(0));
        assertEquals("Third", list.get(2));
        exception.expect(IndexOutOfBoundsException.class);
        list.get(3);
    }

    @Test
    public void testSize() {
        assertEquals(3, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(list.contains(first));
        assertTrue(list.contains(third));
        assertFalse(list.contains("wowzer"));
    }

    @Test
    public void testToObjectArray() {
        Object[] arr = new Object[3];
        arr[0] = first;
        arr[1] = second;
        arr[2] = third;
        Object[] arr2 = list.toArray();
        int i = 0;
        while (i < arr.length) {
            assertEquals(arr[i], arr2[i]);
            i++;
        }
    }

    @Test
    public void testAdd() {
        String s = "Fourth";
        list.add(s);
        assertTrue(list.contains(s));
        assertEquals("Fourth", list.get(3));
    }

    @Test
    public void testAddWithIndex() {
        String s = "SecondSecond";
        list.add(1, s);
        assertEquals(s, list.get(1));
        assertEquals("Second", list.get(2));
        String s2 = "newLast";
        list.add(4, s2);
        assertEquals(s2, list.get(4));
        String s3 = "newFirst";
        list.add(0, s3);
        assertEquals(s3, list.get(0));
        assertEquals(s3, list.getFirst());
        exception.expect(IndexOutOfBoundsException.class);
        list.add(-1, "LELELE");
        list.add(100, "WOWOWOWO");
    }

    @Test
    public void testRemoveObject() {
        String s = "Fourth";
        list.add(s);
        assertFalse(list.remove("wowzaa"));
        list.remove(first);
        assertEquals("Second", list.getFirst());
        assertEquals("Second", list.get(0));
        assertEquals("Third", list.get(1));
        list.remove(third);
        assertEquals("Second", list.getFirst());
        assertEquals("Fourth", list.getLast());
        list.remove(s);
        assertEquals("Second", list.getLast());
        list.remove(second);
        assertTrue(list.isEmpty());
        assertFalse(list.remove("wowzaa"));
    }

    @Test
    public void testRemoveIndex() {
        String s = "Fourth";
        list.add(s);
        assertEquals("First", list.remove(0));
        assertEquals("Second", list.get(0));
        assertEquals("Third", list.get(1));
        assertEquals("Third", list.remove(1));
        assertEquals("Second", list.get(0));
        assertEquals("Second", list.getFirst());
        assertEquals("Fourth", list.getLast());
        assertEquals("Fourth", list.remove(1));
        assertFalse(list.isEmpty());
        assertEquals("Second", list.remove(0));
        assertTrue(list.isEmpty());
        exception.expect(IndexOutOfBoundsException.class);
        list.remove(-1);
        list.remove(1);
    }

    @Test
    public void testAddAll() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Fourth");
        arr.add("Fifth");
        arr.add("Sixth");
        list.addAll(arr);
        assertEquals(6, list.size());
    }

    @Test
    public void testSet() {
        assertEquals("Second", list.set(1, "ThisUsedToBeSecond"));
        assertEquals("ThisUsedToBeSecond", list.get(1));
        exception.expect(IndexOutOfBoundsException.class);
        list.set(-1, "I am trouble");
        list.set(3, "I am more trouble");

    }

    @Test
    public void testIndexOf() {
        assertEquals(0, list.indexOf(first));
        assertEquals(2, list.indexOf(third));
        assertEquals(-1, list.indexOf("WAAZZAAAP"));
    }

    @Test
    public void testLastIndexOf() {
        list.add(first);
        assertEquals(3, list.lastIndexOf(first));
    }

    @Test
    public void testIteration() {
        int i = 0;
        for (String s : list) {
            i++;
        }
        assertEquals(3, i);
        i = 0;
        i = list.stream().map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(3, i);
        ListIterator it = list.listIterator();
        assertEquals("First", it.next());
        assertEquals("First", it.previous());
        assertEquals("First", it.next());
        assertEquals("Second", it.next());

    }

    @Test
    public void testSub() {
        List<String> sub = list.subList(1, 2);
        assertEquals("Second", sub.get(0));
        assertEquals("Third", sub.get(1));
    }

}
