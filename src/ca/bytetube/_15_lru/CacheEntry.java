package ca.bytetube._15_lru;

public interface CacheEntry<V> {
    V getValue();

    void setValue(V value);

    long getLastAccessTime();

    void updateLastAccessTime();
}
