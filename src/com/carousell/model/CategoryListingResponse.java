package com.carousell.model;

import java.util.List;

import com.carousell.v1.model.Listing;

public class CategoryListingResponse extends BaseResponse {

  private List<Listing> categoryListing;


  public List<Listing> getCategoryListing() {
    return categoryListing;
  }

  public void setCategoryListing(List<Listing> categoryListing) {
    this.categoryListing = categoryListing;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.getErrorCodeAndMessage() == null) {
      if(categoryListing.size()> 0) {
        for (Listing listing : categoryListing) {
          sb.append(listing.toString());
          sb.append("\n");
        }
        sb.delete(sb.length()-2, sb.length()); // remove trailing '\n'
      }
      return sb.toString();
    }
    return this.getErrorCodeAndMessage().getMessage();
  }

}
