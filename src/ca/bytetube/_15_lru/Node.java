package ca.bytetube._15_lru;

public class Node<K, V> {
    K key;
    CacheEntry<V> value;
    Node<K, V> prev;
    Node<K, V> next;

    public Node(K key, CacheEntry<V> value) {
        this.key = key;
        this.value = value;
    }
}
