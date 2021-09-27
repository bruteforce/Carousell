package com.carousell.v1.validators;

import java.util.Map;
import java.util.Set;

import com.carousell.cache.CacheManager;
import com.carousell.constants.ApplicationConstants;
import com.carousell.validators.BaseValidator;

public class NewUserValidator implements BaseValidator {

  @Override
  public void validate(Map userProperties) throws Exception {
    // validate user object properties
    String username = (String) userProperties.get(ApplicationConstants.USER_NAME);

    if(((Set)CacheManager.getCacheProvider().get(ApplicationConstants.USERNAMES_SET)).contains(username)){
      throw new Exception(ApplicationConstants.USER_EXISTS);
    }
  }
}
