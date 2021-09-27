package com.carousell.v1.filters;

import java.util.Set;

import com.carousell.cache.CacheManager;
import com.carousell.cache.CacheProvider;
import com.carousell.constants.ApplicationConstants;

public class UserAuthenticationFilter implements Filter {
  private static CacheProvider cacheProvider;

  static {
    cacheProvider = CacheManager.getCacheProvider();
  }

  @Override
  public void doFilter(Object username) throws Exception {
    if(((Set)cacheProvider.get(ApplicationConstants.USERNAMES_SET)).contains(username)){
      return;
    }
    throw new Exception(ApplicationConstants.UNKNOWN_USER);
  }
}