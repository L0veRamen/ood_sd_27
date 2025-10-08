package ca.bytetube._15_lru;

public interface Cache<K, V> {
    V get(K key);

    void put(K key, V value);

    void remove(K key);

    void clear();

    int size();

}
