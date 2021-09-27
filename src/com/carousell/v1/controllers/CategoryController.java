package com.carousell.v1.controllers;

import com.carousell.model.CategoryListingResponse;
import com.carousell.model.ErrorCodeAndMessage;
import com.carousell.model.GetCategoryListingResponse;
import com.carousell.model.TopCategoryResponse;
import com.carousell.v1.model.Listing;
import com.carousell.v1.processors.CategoryProcessor;
import com.carousell.v1.processors.ListingProcessor;
import com.carousell.v1.processors.impl.CategoryProcessorImpl;
import com.carousell.v1.processors.impl.ListingProcessrImpl;

public class CategoryController {

  private static CategoryProcessor categoryProcessor;

  public CategoryController() {
    categoryProcessor = new CategoryProcessorImpl();
  }

  /**
   * - GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}
   *
   * Responses:
   * - "Error - category not found"
   * - "Error - unknown user"
   * - "<title>|<description>|<price>|<created_at>
   * <title>|<description>|<price>|<created_at>
   * <title>|<description>|<price>|<created_at>
   *
   *
   * @param name
   * @return
   */
  public CategoryListingResponse getCategoryListing(String categoryName, String sortBy,
      String order){
    CategoryListingResponse categoryListingResponse = new CategoryListingResponse();
    try {
      categoryListingResponse
          .setCategoryListing(Listing
              .sortListings(categoryProcessor.getListingsFromCategory(categoryName), sortBy, order));
    }catch(Exception ex){
      categoryListingResponse.setErrorCodeAndMessage( new ErrorCodeAndMessage());
      categoryListingResponse.getErrorCodeAndMessage().setMessage(ex.getMessage());
    }
    return categoryListingResponse;
  }

  /**
   * - GET_TOP_CATEGORY <username>
   * - Responses:
   * - "Error - unknown user"
   * - <category name> (Category having the highest total number of listings).
   * This command should consider all listings in the marketplace and not just
   * the listings created by the user issuing the command. Username is taken
   * just for the purpose of authentication.
   * - This operation is expected to be a read heavy operation as it can be used
   * on the home page etc. Please ensure suitable optimization for the same.
   * @return
   */
  public TopCategoryResponse getTopCategory(){
    TopCategoryResponse topCategoryResponse = new TopCategoryResponse();
    topCategoryResponse.setTopCategory(categoryProcessor.getTopCategoryForUser());
    return topCategoryResponse;
  }
}
