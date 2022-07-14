package me.tbhmens.TheSource.util;

import java.util.ArrayList;
import java.util.Random;

public class WeightedList<I> {
    static class Weighted<T> {
        T item;
        double weight;

        Weighted(T item, double weight) {
            this.item = item;
            this.weight = weight;
        }
    }

    ArrayList<Weighted<I>> items = new ArrayList<>();
    double totalWeight = 0;

    public WeightedList() {
    }

    public void add(I item, double weight) {
        items.add(new Weighted<>(item, weight));
        totalWeight += weight;
    }

    Random random = new Random();

    public I getRandom() {
        double rand = random.nextDouble() * totalWeight;
        double total = 0.0;
        for (var item : items) {
            total += item.weight;
            if (total >= rand)
                return item.item;
        }
        return null; // should never get here.
    }
}
