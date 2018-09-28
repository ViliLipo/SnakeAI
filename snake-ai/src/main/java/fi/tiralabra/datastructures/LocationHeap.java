/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import fi.tiralabra.game.Location;

/**
 * Special heap that sorts locations based on distance to goal
 *
 * @author vili
 */
public class LocationHeap {

    private Location goal;

    private Location[] table;

    private int heapSize;

    public LocationHeap(Location goal) {
        this.goal = goal;
        this.table = new Location[100];
        heapSize = 0;
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
        if (l <= this.heapSize-1 && goal.distance(this.table[l]) < goal.distance(this.table[i])) {
            closest = l;
        } else {
            closest = i;
        }
        if (r <= this.heapSize-1 && goal.distance(this.table[r]) < goal.distance(this.table[closest])) {
            closest = r;
        }
        if (closest != i) {
            exhange(i, closest);
            minHeapify(closest);
        }
    }

    private void exhange(int i, int j) {
        Location iL = this.table[i];
        Location jL = this.table[j];
        this.table[i] = jL;
        this.table[j] = iL;
    }

    public void buildHeap(Location[] A) {
        this.table = A;
        this.heapSize = A.length;
        for (int i = A.length / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    private void increaseKey(int i, Location key) {
        if (this.table[i] != null) {
            System.out.println("BIGGER DISTANCE");
            return;
        }
        this.table[i] = key;
        int dist1 = goal.distance(table[parent(i)]);
        int dist2 = goal.distance(this.table[i]);
        while (i > 0 && dist1 > dist2) {
            exhange(i, parent(i));
            i = parent(i);
        }
    }

    public void insert(Location loc) {
        this.heapSize++;
        if (this.heapSize > this.table.length) {
            allocateMoreTable();
        }
        this.table[this.heapSize -1] = null;
        increaseKey(this.heapSize-1, loc);
    }

    public Location extractClosest() {
        if (this.heapSize < 0) {
            return null;
        }
        Location closest = this.table[0];
        this.table[0] = this.table[this.heapSize];
        this.heapSize--;
        minHeapify(0);
        return closest;
    }

    private void allocateMoreTable() {
        Location[] newTable = new Location[this.table.length * 2];
        System.arraycopy(this.table, 0, newTable, 0, this.table.length);
        this.table = newTable;
    }

}
