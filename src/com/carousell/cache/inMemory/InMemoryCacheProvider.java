package com.carousell.cache.inMemory;

import java.util.concurrent.ConcurrentHashMap;

import com.carousell.cache.CacheProvider;

public class InMemoryCacheProvider implements CacheProvider<String, Object> {

  private ConcurrentHashMap<String, Object> cacheMap;

  public InMemoryCacheProvider() {
    cacheMap = new ConcurrentHashMap<String, Object>();
  }

  @Override
  public Object get(String key) {
    return cacheMap.get(key);
  }

  @Override
  public void put(String key, Object value) {
    cacheMap.put(key, value);
  }

  @Override
  public void remove(String key) {
    cacheMap.remove(key);
  }

  @Override
  public void clear() {
  }
}


