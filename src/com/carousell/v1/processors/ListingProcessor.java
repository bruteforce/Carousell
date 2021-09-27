package com.carousell.v1.processors;


import com.carousell.v1.model.Listing;

public interface ListingProcessor {

  long create(Listing listing);

  boolean delete(long listing_id, String username) throws Exception;

  Listing get(long listing_id);

}
