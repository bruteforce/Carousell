package com.carousell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carousell.constants.ApplicationConstants;
import com.carousell.v1.controllers.CategoryController;
import com.carousell.v1.controllers.ListingController;
import com.carousell.v1.controllers.UserController;
import com.carousell.v1.filters.Filter;
import com.carousell.v1.filters.UserAuthenticationFilter;

/**
 * Dispatcher is where all the requests will land. Dispatcher routes the call to it's respective Controller.
 */
public class Dispatcher {

  private static List<Filter> filters;
  private static Map<String, Class> endpointToControllerMapping;

  static {
    initialise();
  }

  private static void initialise() {
    // load filters
    filters = new ArrayList<>();
    filters.add(new UserAuthenticationFilter());

  }

  public static String process(String[] arguments) {
    // execute filters
    for (Filter filter : filters) {
      if (filter.getClass() == UserAuthenticationFilter.class) {
        try {
          if (arguments[0].equals(ApplicationConstants.REGISTER)) {
            continue;
          }
          filter.doFilter(arguments[1]);
        } catch (Exception e) {
          return e.getMessage();
        }
      }
    }

    // identify and execute controller method
    switch (arguments[0]) {
      case ApplicationConstants.REGISTER:
        return new UserController().registerUser(arguments[1]).toString();
      case ApplicationConstants.CREATE_LISTING:
        return new ListingController().createListing(arguments[1], arguments[2], arguments[3],
            Double.parseDouble(arguments[4]), arguments[5]).toString();
      case ApplicationConstants.GET_CATEGORY:
        return new CategoryController().getCategoryListing(arguments[2], arguments[3], arguments[4]).toString();
      case ApplicationConstants.GET_TOP_CATEGORY:
        return new CategoryController().getTopCategory().toString();
      case ApplicationConstants.DELETE_LISTING:
        return new ListingController().deleteListing(arguments[1], arguments[2]).toString();
      case ApplicationConstants.GET_LISTING:
        return new ListingController().getListingFromId(Long.parseLong(arguments[2])).toString();
    }
    return ApplicationConstants.NO_SUCH_OPERATION_DEFINED;
  }

}
