package com.carousell.constants;

public class ApplicationConstants {

  public static final String USER_NAME = "username";
  public static final String SUCCESS_MESSAGE = "Success";

  // Endpoints
  public static final String REGISTER = "REGISTER";
  public static final String CREATE_LISTING = "CREATE_LISTING";
  public static final String GET_LISTING = "GET_LISTING";
  public static final String DELETE_LISTING = "DELETE_LISTING";
  public static final String GET_TOP_CATEGORY = "GET_TOP_CATEGORY";
  public static final String GET_CATEGORY = "GET_CATEGORY";

  // cache keys
  public static final String USERNAMES_SET = "USERNAMES_SET";
  public static final String USER_LISTING_MAP = "USER_LISTING_MAP";
  public static final String CATEGORY_LISTING_MAP = "CATEGORY_LISTING_MAP";
  public static final String LISTING_ID = "LISTING_ID";
  public static final String LISTING_MAP = "LISTING_MAP";
  public static final String CATEGORY_MAP = "CATEGORY_MAP";
  public static final String TOP_CATEGORY = "TOP_CATEGORY";

  // Errors
  public static final String ERROR_LISTING_MISMATCH = "Error - listing owner mismatch";
  public static final String NO_SUCH_OPERATION_DEFINED = "Error - Not such endpoint defined";
  public static final String CATEGORY_NOT_FOUND = "Error - category not found";
  public static final String UNKNOWN_USER = "Error - unknown user";
  public static final String USER_EXISTS = "Error - user already existing";
}
