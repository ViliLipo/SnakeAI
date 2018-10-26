/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import java.util.Comparator;

/**
 * Typed Binary heap that functions as a minHeap
 *
 * @author vili
 */
public class BinaryHeap<E> {

    private E[] table;
    private int heapSize;
    private Comparator comp;

    /**
     *
     * @param s size
     * @param comp comparator that is used in ordering
     */
    public BinaryHeap(int s, Comparator comp) {
        this.table = (E[]) new Object[s];
        this.heapSize = 0;
        this.comp = comp;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return (2 * i) + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int closest;
        if (l <= this.heapSize && (comp.compare(this.table[l], this.table[i]) < 0)) {
            closest = l;
        } else {
            closest = i;
        }
        if (r <= this.heapSize && (comp.compare(this.table[r], this.table[closest]) < 0)) {
            closest = r;
        }
        if (closest != i) {
            exhange(i, closest);
            minHeapify(closest);
        }
    }

    private void exhange(int i, int j) {
        E eli = table[i];
        E elj = table[j];
        table[j] = eli;
        table[i] = elj;
    }

    /**
     * Build a heap from array of elements
     *
     * @param array Build this array into a heap.
     */
    public void buildHeap(E[] array) {
        this.heapSize = 0;
        this.table = (E[]) new Object[100];
        for (E element : array) {
            if (element != null) {
                this.insert(element);
            }
        }
    }

    private void increaseKey(int i, E key) {
        if (this.table[i] != null) {
            return;
        }
        this.table[i] = key;
        while (i > 0 && comp.compare(table[parent(i)], table[i]) == 1) {
            exhange(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Add a Element to this heap
     *
     * @param e Element to be added
     */
    public void insert(E e) {
        this.heapSize++;
        if (this.heapSize > this.table.length) {
            allocateMoreTable();
        }
        this.table[this.heapSize - 1] = null;
        increaseKey(this.heapSize - 1, e);
    }

    /**
     * Get the element with the smallest comparator value.
     *
     * @return The element with the smallest comparator value.
     */
    public E extract() {
        if (this.heapSize <= 0) {
            return null;
        }
        E closest = this.table[0];
        this.table[0] = this.table[this.heapSize - 1];
        this.heapSize--;
        minHeapify(0);
        return closest;
    }

    private void allocateMoreTable() {
        E[] newTable = (E[]) new Object[this.heapSize * 2];
        System.arraycopy(this.table, 0, newTable, 0, this.table.length);
        this.table = newTable;
    }

    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public int getSize() {
        return this.heapSize;
    }

}
