/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author vili
 * @param <E> the type for the value in the list
 */
public class LinkedList<E> implements List<E> {

    private ListItem<E> first;
    private ListItem<E> last;

    private class ListItem<E> {

        private ListItem<E> next;
        private ListItem<E> prev;
        private E value;

        ListItem(final E element) {
            this.value = element;
        }

        E getValue() {
            return this.value;
        }

        void setValue(E value) {
            this.value = value;
        }
    }

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public int size() {
        ListItem index = this.first;
        int size = 0;
        while (index.next != null) {
            size++;
            index = index.next;
        }
        return size;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public boolean contains(Object o) {
        ListItem index = this.first;
        while (index.next != null) {
            Object value = (Object) index.value;
            if (value.equals(o)) {
                return true;
            }
            index = index.next;
        }
        return false;
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        int i = 0;
        ListItem index = this.first;
        while (index.next != null) {
            array[i] = (Object) index.value;
            i++;
            index = index.next;
        }
        return array;
    }

    public <T> T[] toArray(T[] a) {
        int i = 0;
        ListItem<E> index = this.first;
        while (index.next != null) {
            a[i] = (T) index.value;
            i++;
            index = index.next;
        }
        return a;
    }

    public final boolean add(E e) {
        if (this.first == null) {
            this.first = new ListItem(e);
            return true;
        }
        ListItem<E> newElement = new ListItem(e);
        ListItem index = this.last;
        while (index.next != null) {
            index = index.next;
        }
        index.next = newElement;
        newElement.prev = index;
        this.last = newElement;
        return true;
    }

    public boolean remove(Object o) {
        ListItem<E> index = this.first;
        while (index.next != null) {
            Object value = (Object) index.value;
            if (value.equals(o)) {
                if (index.prev != null && index.next != null) {
                    ListItem<E> previous = index.prev;
                    ListItem<E> nextI = index.next;
                    previous.next = nextI;
                    nextI.prev = previous;
                } else if (index.prev != null && index.next == null) {
                    ListItem<E> previous = index.prev;
                    previous.next = null;
                    this.last = previous;
                } else if (index.prev == null && index.next != null) {
                    ListItem<E> nextI = index.next;
                    nextI.prev = null;
                    this.first = nextI;
                } else {
                    this.first = null;
                    this.last = null;
                }
                return true;
            }
            index = index.next;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E o : c) {
            this.add(o);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        this.first = null;
        this.last = null;
    }

    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem<E> pointer = this.first;
        while (pointer.next != null) {
            if (count == index) {
                return pointer.value;
            } else {
                count++;
                pointer = pointer.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public E set(int index, E element) {
        if (index <= this.size() || index > 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem<E> pointer = this.first;
        while (pointer.next != null) {
            if (count == index) {
                E oldvalue = pointer.value;
                pointer.value = element;
                return oldvalue;
            }
            pointer = pointer.next;
            count++;
        }
        return null;
    }

    public void add(int index, E element) {
        if (index <= this.size() || index > 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem newItem = new ListItem(element);
        ListItem<E> pointer = this.first;
        while (pointer.next != null) {
            if (count == index) {
                if (pointer.prev != null) {
                    ListItem previous = pointer.prev;
                    pointer.prev = newItem;
                    newItem.next = pointer;
                    previous.next = newItem;
                    newItem.prev = previous;
                } else if (pointer.prev == null) {
                    // case of adding on 0
                    newItem.prev = null;
                    pointer.prev = newItem;
                    newItem.next = pointer;
                    this.first = newItem;
                }
            }
            count++;
            pointer = pointer.next;
        }
    }

    public E remove(int index) {
        if (index <= this.size() || index > 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem<E> pointer = this.first;
        while (pointer.next != null) {
            if (count == index) {
                if (pointer.next == null && pointer.prev == null) {
                    // case removing only item
                    this.first = null;
                    this.last = null;
                } else if (pointer.next == null && pointer.prev != null) {
                    // case removing last item
                    ListItem<E> previous = pointer.prev;
                    this.last = previous;
                    previous.next = null;
                } else if (pointer.next != null && pointer.prev == null) {
                    // case removing first item
                    ListItem<E> nextI = pointer.next;
                    nextI.prev = null;
                    this.first = nextI;
                } else if (pointer.next != null && pointer.prev != null) {
                    // case removing item in middle
                    ListItem<E> previous = pointer.prev;
                    ListItem<E> next = pointer.next;
                    next.prev = previous;
                    previous.next = next;
                }
                return pointer.value;
            }
            pointer = pointer.next;
            count++;
        }
        return null;
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
