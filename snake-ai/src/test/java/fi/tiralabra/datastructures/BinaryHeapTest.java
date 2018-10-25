/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class BinaryHeapTest {
    
    private BinaryHeap<Integer> heap;
    
    public BinaryHeapTest() {
    }
    
    @Before
    public void setUp() {
        Comparator<Integer> intcomp = new Comparator<Integer>() {
            @Override
            public int compare (Integer e1, Integer e2) {
                return e1.compareTo(e2);
            }
        };
        this.heap = new BinaryHeap<>(100, intcomp);
    }
    

    @Test
    public void testBuildHeap() {
        Integer[] arr = new Integer[5];
        arr[0] = 6;
        arr[1] = 8;
        heap.buildHeap(arr);
    }

    @Test
    public void testInsert() {
        heap.insert(6);
    }


    @Test
    public void testExtract() {
        heap.insert(Integer.MIN_VALUE);
        heap.insert(Integer.MAX_VALUE);
        heap.insert(3);
        heap.insert(0);
        heap.insert(1);
        assertEquals(5, heap.getSize());
        int value = heap.extract();
        assertEquals(Integer.MIN_VALUE, value);
        value = heap.extract();
        assertEquals(0, value);
        value = heap.extract();
        assertEquals(1, value);
        value = heap.extract();
        assertEquals(3, value);
        value = heap.extract();
        assertEquals(Integer.MAX_VALUE, value);
        assertEquals(0, heap.getSize());
    }
    @Test
    public void testAllocateMore() {
        for(int i =0; i < 1000; i++) {
            heap.insert(i);
        }
        assertEquals(1000, heap.getSize());
    }
    
}
