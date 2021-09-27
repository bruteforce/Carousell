package com.carousell.v1.controllers;

import com.carousell.constants.ApplicationConstants;
import com.carousell.model.DeleteListingResponse;
import com.carousell.model.ErrorCodeAndMessage;
import com.carousell.model.ListingResponse;
import com.carousell.model.RequestMap;
import com.carousell.model.CreateListingResponse;
import com.carousell.v1.model.Listing;
import com.carousell.v1.processors.ListingProcessor;
import com.carousell.v1.processors.impl.ListingProcessrImpl;

public class ListingController {

  private static ListingProcessor listingProcessor;

  public ListingController() {
    listingProcessor = new ListingProcessrImpl();
  }

  /**
   * - CREATE_LISTING <username> <title> <description> <price> <category>
   *
   * Responses:
   * - "<listing id>"
   * - "Error - unknown user"
   * @param userName
   * @param title
   * @param description
   * @param price
   * @param categoryName
   * @return
   */
   public CreateListingResponse createListing(String userName, String title,  String description, double price, String categoryName){
     CreateListingResponse createListingResponse = new CreateListingResponse();
     long listing_id = listingProcessor.create(new Listing(title, description, price, categoryName, userName));
     createListingResponse.setListing_id(""+listing_id);
     return createListingResponse;
   }

  /**
   * - DELETE_LISTING <username> <listing_id>
   * Responses:
   * - "Success"
   * - "Error - listing does not exist"
   * - "Error - listing owner mismatch"
   * @return
   */
   public DeleteListingResponse deleteListing(String userName, String lisitng_id){
     DeleteListingResponse deleteListingResponse = new DeleteListingResponse();
     try {
       if (listingProcessor.delete(Long.parseLong(lisitng_id), userName)) {
         deleteListingResponse.setStatus(ApplicationConstants.SUCCESS_MESSAGE);
       }
     } catch(Exception ex){
       ErrorCodeAndMessage ecm = new ErrorCodeAndMessage();
       ecm.setMessage(ex.getMessage());
       deleteListingResponse.setErrorCodeAndMessage(ecm);
     }
     return deleteListingResponse;
   }

  /**
   * - GET_LISTING <username> <listing_id>
   * - Responses:
   * - "<title>|<description>|<price>|<created_at>|<category>|<username>"
   * This command should return any listing queried according to listing_id,
   * not limited to listings created by the user. Username is taken just for the
   * purpose of authentication.
   * - "Error - not found"
   * - "Error - unknown user"
   * @param listing_id
   * @return
   */
   public ListingResponse getListingFromId(long listing_id){
     ListingResponse listingResponse = new ListingResponse();
     listingResponse.setListing(listingProcessor.get(listing_id));
     return listingResponse;
   }

}
