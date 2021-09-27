package com.carousell.v1.processors.impl;

import java.util.HashSet;
import java.util.Set;

import com.carousell.cache.CacheManager;
import com.carousell.cache.CacheProvider;
import com.carousell.constants.ApplicationConstants;
import com.carousell.v1.processors.UserProcessor;

public class UserProcessorImpl implements UserProcessor {

  private static CacheProvider cacheProvider;

  static {
    cacheProvider = CacheManager.getCacheProvider();
    cacheProvider.put(ApplicationConstants.USERNAMES_SET, new HashSet<String>());
  }

  public UserProcessorImpl() {

  }

  @Override
  public synchronized boolean register(String username) {
    return ((Set) cacheProvider.get(ApplicationConstants.USERNAMES_SET)).add(username);
  }
}
