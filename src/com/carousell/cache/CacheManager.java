package com.carousell.cache;

import com.carousell.cache.inMemory.InMemoryCacheProvider;

public class CacheManager {

  private static CacheProvider cacheProvider;

  public static CacheProvider getCacheProvider(CacheType cacheType)
      throws Exception {
    if (cacheType.equals(CacheType.IN_MEMORY)) {
        if (cacheProvider == null) {
          synchronized (CacheManager.class) {
            if (cacheProvider == null) {
              cacheProvider = new InMemoryCacheProvider();
            }
          }
        }
      return cacheProvider;
    }
    throw new Exception("Cache Type not Implemented yet " + cacheType);
  }

  public static CacheProvider getCacheProvider() {
    try {
      return getCacheProvider(CacheType.IN_MEMORY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
