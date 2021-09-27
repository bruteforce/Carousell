package com.carousell.model;

public class CreateListingResponse extends BaseResponse{

  private String listing_id;

  public String getListing_id() {
    return listing_id;
  }

  public void setListing_id(String listing_id) {
    this.listing_id = listing_id;
  }

  @Override
  public String toString() {
    if(this.getErrorCodeAndMessage() == null)
      return listing_id;
    else
      return this.getErrorCodeAndMessage().getMessage();
  }
}
