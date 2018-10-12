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
import java.util.NoSuchElementException;
import java.util.function.Consumer;

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

    }

    private class LinkedListIterator implements ListIterator<E> {

        private final LinkedList<E> iterable;
        private ListItem<E> pointer;
        private int index;

        LinkedListIterator(LinkedList<E> list) {
            this.iterable = list;
            this.pointer = this.iterable.first;
            this.index = -1;
        }

        @Override
        public E next() {
            if (this.pointer == null) {
                throw new NoSuchElementException();
            }
            E value = this.pointer.value;
            this.pointer = this.pointer.next;
            index++;
            return value;
        }

        @Override
        public boolean hasNext() {
            return this.pointer != null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            while (this.hasNext()) {
                action.accept(this.next());
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Adds element to the list
         *
         */
        @Override
        public void add(E e) {
            this.iterable.add(e);
        }

        @Override
        public E previous() {
            if (this.pointer.prev == null) {
                throw new NoSuchElementException();
            }
            this.pointer = this.pointer.prev;
            E value = this.pointer.value;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return (this.pointer.prev != null);
        }

        @Override
        public int nextIndex() {
            return this.index + 1;
        }

        @Override
        public int previousIndex() {
            return this.index - 1;
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public int size() {
        ListItem<E> index = this.first;
        int size = 0;
        while (index != null) {
            size++;
            index = index.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean contains(Object o) {
        ListItem<E> index = this.first;
        while (index != null) {
            Object value = (Object) index.value;
            if (value.equals(o)) {
                return true;
            }
            index = index.next;
        }
        return false;
    }

    public E getFirst() {
        return this.first.value;
    }

    public E getLast() {
        return this.last.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        int i = 0;
        ListItem<E> index = this.first;
        while (index != null) {
            array[i] = (Object) index.value;
            i++;
            index = index.next;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        ListItem<E> index = this.first;
        while (index != null) {
            a[i] = (T) index.value;
            i++;
            index = index.next;
        }
        return a;
    }

    @Override
    public final boolean add(E e) {
        ListItem<E> newElement = new ListItem<E>(e);
        if (this.first == null) {
            this.first = newElement;
            this.last = newElement;
            return true;
        }
        ListItem<E> index = this.last;
        index.next = newElement;
        newElement.prev = index;
        this.last = newElement;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        ListItem<E> index = this.first;
        while (index != null) {
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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o : c) {
            this.add(o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem<E> pointer = this.first;
        while (pointer != null) {
            if (count == index) {
                return pointer.value;
            } else {
                count++;
                pointer = pointer.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        if (index >= this.size() || index < 0) {
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

    @Override
    public void add(int index, E element) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.size()) {
            this.add(element);
            return;
        }
        int count = 0;
        ListItem<E> newItem = new ListItem<>(element);
        ListItem<E> pointer = this.first;
        while (pointer != null) {
            if (count == index) {
                if (pointer.prev != null) {
                    ListItem<E> previous = pointer.prev;
                    pointer.prev = newItem;
                    newItem.next = pointer;
                    previous.next = newItem;
                    newItem.prev = previous;
                } else {
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

    public void addFirst(E element) {
        this.add(0, element);
    }

    private E removeFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        E value = this.first.value;
        this.first = this.first.next;
        if (this.first != null) {
            this.first.prev = null;
        } else {
            this.last = null;
        }
        return value;
    }

    private E removeLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        E value = this.last.value;
        this.last = this.last.prev;
        if (this.last != null) {
            this.last.next = null;
        } else {
            this.first = null;
        }
        return value;
    }

    private void removeFromMiddle(ListItem<E> element) {
        ListItem<E> previous = element.prev;
        ListItem<E> next = element.next;
        next.prev = previous;
        previous.next = next;
    }

    @Override
    public E remove(int index) {
        if (index >= this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListItem<E> pointer = this.first;
        while (pointer != null) {
            if (count == index) {
                if (pointer.next == null && pointer.prev == null) {
                    // case removing only item
                    this.first = null;
                    this.last = null;
                } else if (pointer.next == null && pointer.prev != null) {
                    // case removing last item
                    this.removeLast();
                } else if (pointer.next != null && pointer.prev == null) {
                    // case removing first item
                    this.removeFirst();
                } else if (pointer.next != null && pointer.prev != null) {
                    // case removing item in middle
                    this.removeFromMiddle(pointer);
                }
                return pointer.value;
            }
            pointer = pointer.next;
            count++;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        ListItem<E> pointer = this.first;
        int index = 0;
        while (pointer != null) {
            Object value = (Object) pointer.value;
            if (value.equals(o)) {
                return index;
            }
            index++;
            pointer = pointer.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListItem<E> pointer = this.first;
        int index = 0;
        int lastIndex = -1;
        while (pointer != null) {
            Object value = (Object) pointer.value;
            if (value.equals(o)) {
                lastIndex = index;
            }
            index++;
            pointer = pointer.next;
        }
        return lastIndex;
    }

    public E poll() {
        return this.removeFirst();
    }

    public E pop() {
        return this.removeLast();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator(this);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        ListIterator<E> it = new LinkedListIterator(this);
        int count = 0;
        while (count < index && it.hasNext()) {
            it.next();
            count++;
        }
        return it;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        ListIterator<E> it = this.listIterator(fromIndex);
        LinkedList<E> sub = new LinkedList<>();
        while (it.hasNext() && fromIndex < toIndex) {
            sub.add(it.next());
        }
        return sub;
    }

}
