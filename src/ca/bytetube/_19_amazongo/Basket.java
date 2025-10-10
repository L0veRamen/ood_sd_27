package ca.bytetube._19_amazongo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
    Map<Product, Integer> items = new LinkedHashMap<>();


    public void add(Product product, int qty) {
        items.put(product, items.getOrDefault(product, 0) + qty);
        if (items.get(product) <= 0) items.remove(product);
    }

    public Map<Product, Integer> view() {
        return items;
    }


}
