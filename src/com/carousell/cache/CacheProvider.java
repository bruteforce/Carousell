package com.carousell.cache;

public interface CacheProvider<K, V> {

  V get(K key);

  void put(K key, V value);

  void remove(K key);

  void clear();
}
