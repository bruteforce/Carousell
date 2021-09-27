package com.carousell.model;

import com.carousell.v1.model.Listing;

public class ListingResponse extends BaseResponse {

  private Listing listing;

  public Listing getListing() {
    return listing;
  }

  public void setListing(Listing listing) {
    this.listing = listing;
  }

  @Override
  public String toString() {
//    Phone model 8|Black color, brand new|1000|2019-02-22 12:34:56|Electronics|user1
    return this.getListing().getTitle() + "|" + this.getListing().getDescription() + "|" + this.getListing().getPrice()
        + "|" + this.getListing().getCreationTime()+ "|"+this.getListing().getCategoryName()+"|"+this.getListing().getUserName();
  }

}
