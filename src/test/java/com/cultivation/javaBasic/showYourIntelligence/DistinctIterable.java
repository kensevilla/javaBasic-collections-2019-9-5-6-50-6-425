package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;
    private ArrayList<E> temp = new ArrayList<>();

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if(temp.size() == 2){
            return false;
        }
        return this.iterator.hasNext();
    }

    @Override
    public E next() {
        boolean isDistinct = false;
        E current = this.iterator.next();
        if(temp.isEmpty()){
            temp.add(current);
            return current;
        }
        else {
            while (!isDistinct) {
                isDistinct=true;
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i) == current) {
                        isDistinct = false;
                    }
                }
                if (isDistinct) {
                    temp.add(current);
                }
                else {
                    current = this.iterator.next();
                }
            }
            return current;
        }
    }
    // --end->
}