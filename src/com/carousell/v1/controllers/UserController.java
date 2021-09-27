package com.carousell.v1.controllers;

import java.util.HashMap;
import java.util.Map;

import com.carousell.constants.ApplicationConstants;
import com.carousell.model.ErrorCodeAndMessage;
import com.carousell.model.UserResponseBody;
import com.carousell.v1.processors.UserProcessor;
import com.carousell.v1.processors.impl.UserProcessorImpl;
import com.carousell.v1.validators.NewUserValidator;

public class UserController {

  private static UserProcessor userProcessor;

  public UserController() {
    userProcessor = new UserProcessorImpl();
  }

  /**
   * - REGISTER <username> Responses: - "Success" - "Error - user already existing"
   */
  public UserResponseBody registerUser(String userName) {
    UserResponseBody userResponseBody = new UserResponseBody();
    try {
      // Set properties that needs to be validated in Validators.
      Map<String, String> userDataMap = new HashMap<>();
      userDataMap.put(ApplicationConstants.USER_NAME, userName);
      new NewUserValidator().validate(userDataMap);
      if (userProcessor.register(userName)) {
        userResponseBody.setUsername(ApplicationConstants.SUCCESS_MESSAGE);
      }
    } catch (Exception ex) {
//      System.out.println("Exception occured while registering user with username: " + userName);
      ErrorCodeAndMessage erm = new ErrorCodeAndMessage();
      erm.setMessage(ex.getMessage());
      userResponseBody.setErrorCodeAndMessage(erm);
      return userResponseBody;
    }
    return userResponseBody;
  }

}
