package com.carousell.v1.processors.impl;

import static com.carousell.constants.ApplicationConstants.CATEGORY_LISTING_MAP;
import static com.carousell.constants.ApplicationConstants.LISTING_MAP;
import static com.carousell.constants.ApplicationConstants.TOP_CATEGORY;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.carousell.cache.CacheManager;
import com.carousell.cache.CacheProvider;
import com.carousell.constants.ApplicationConstants;
import com.carousell.v1.model.Category;
import com.carousell.v1.model.Listing;
import com.carousell.v1.processors.CategoryProcessor;

public class CategoryProcessorImpl implements CategoryProcessor {

  private static CacheProvider cacheProvider;


  static {
    cacheProvider = CacheManager.getCacheProvider();
  }

  @Override
  public List<Listing> getListingsFromCategory(String categoryName) throws Exception {
    Map<String, Set<Long>> categoryListingMap = (Map<String, Set<Long>>) cacheProvider
        .get(CATEGORY_LISTING_MAP);
    Map<String, Listing> listingMap = (Map<String, Listing>) cacheProvider.get(LISTING_MAP);
    List<Listing> listings = new ArrayList<>();
    if(!categoryListingMap.containsKey(categoryName))
      throw new Exception(ApplicationConstants.CATEGORY_NOT_FOUND);
    for (Long listingID : categoryListingMap.get(categoryName)) {
      listings.add(listingMap.get(listingID));
    }
//    Collections.sort(listings, (l1, l2) ->{
//      return Integer.compare(l1.getCreationTime(), l2.getCreationTime());
//    });
    return listings;
  }

  @Override
  public String getTopCategoryForUser() {
    PriorityQueue<Category> topCategoryQueue = (PriorityQueue<Category>) cacheProvider.get(TOP_CATEGORY);
    if(topCategoryQueue.isEmpty())
      return "No categories found";
    return topCategoryQueue.peek().getType();
  }
}
