package com.carousell.v1.processors.impl;

import static com.carousell.constants.ApplicationConstants.CATEGORY_LISTING_MAP;
import static com.carousell.constants.ApplicationConstants.CATEGORY_MAP;
import static com.carousell.constants.ApplicationConstants.LISTING_ID;
import static com.carousell.constants.ApplicationConstants.LISTING_MAP;
import static com.carousell.constants.ApplicationConstants.TOP_CATEGORY;
import static com.carousell.constants.ApplicationConstants.USER_LISTING_MAP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.carousell.cache.CacheManager;
import com.carousell.cache.CacheProvider;
import com.carousell.constants.ApplicationConstants;
import com.carousell.v1.model.Category;
import com.carousell.v1.model.Listing;
import com.carousell.v1.processors.ListingProcessor;

public class ListingProcessrImpl implements ListingProcessor {

  private static CacheProvider cacheProvider;


  static {
    cacheProvider = CacheManager.getCacheProvider();
    cacheProvider
        .put(USER_LISTING_MAP, new HashMap<String, Set<Long>>()); // UserName to Listing Map
    cacheProvider
        .put(CATEGORY_LISTING_MAP, new HashMap<String, Set<Long>>()); // Category to ListingID Map
    cacheProvider.put(LISTING_ID, 100001L);
    cacheProvider.put(LISTING_MAP, new HashMap<Long, Listing>());
    cacheProvider.put(CATEGORY_MAP, new HashMap<String, Category>());
    cacheProvider.put(TOP_CATEGORY, new PriorityQueue<Category>((c1, c2)-> Integer.compare(c2.getCount(), c1.getCount())));
  }

  public ListingProcessrImpl() {
  }

  @Override
  public synchronized long create(Listing listing) {
    String categoryName = listing.getCategoryName();
    Map<String, Set<Long>> userListingMap = (Map<String, Set<Long>>) cacheProvider
        .get(USER_LISTING_MAP);
    Map<String, Set<Long>> categoryListingMap = (Map<String, Set<Long>>) cacheProvider
        .get(CATEGORY_LISTING_MAP);
    Queue<Category> topCategoryQueue = (PriorityQueue<Category>) cacheProvider.get(TOP_CATEGORY);

    // Set Listing ID
    long listing_id = (long) cacheProvider.get(LISTING_ID);
    listing.setId(listing_id);
    cacheProvider.put(LISTING_ID, listing_id + 1);


    // Update listing Map.
    ((Map<Long, Listing>) cacheProvider.get(LISTING_MAP)).put(listing_id, listing);

    // Update Category to Listing Map
    categoryListingMap.putIfAbsent(listing.getCategoryName(), new HashSet<>());
    categoryListingMap.get(listing.getCategoryName()).add(listing.getId());


    // Update Category Map && Top Category queue.
    Map<String, Category> categoryMap = ((Map<String, Category>) cacheProvider.get(CATEGORY_MAP));
    categoryMap.putIfAbsent(listing.getCategoryName(), new Category(listing.getCategoryName(), 0));
    if(categoryMap.get(categoryName).getCount() > 0){
      topCategoryQueue.remove(categoryMap.get(categoryName));
    }
    categoryMap.get(categoryName).setCount(1+categoryMap.get(categoryName).getCount());
    topCategoryQueue.add(categoryMap.get(categoryName));


    // Update User To Listing Map
    userListingMap.putIfAbsent(listing.getUserName(), new HashSet<>());
    userListingMap.get(listing.getUserName()).add(listing.getId());

    return listing_id;
  }

  @Override
  public synchronized boolean delete(long listing_id, String username) throws Exception {
    Queue<Category> topCategoryQueue = (PriorityQueue<Category>) cacheProvider.get(TOP_CATEGORY);
    String categoryName = ((Map<Long, Listing>) cacheProvider.get(LISTING_MAP)).get(listing_id).getCategoryName();
    Map<String, Set<String>> userListingMap = (Map<String, Set<String>>) cacheProvider
        .get(USER_LISTING_MAP);
    if (!userListingMap.get(username).contains(listing_id)) {
      throw new Exception(ApplicationConstants.ERROR_LISTING_MISMATCH);
    }

    userListingMap.get(username).remove(listing_id);

    Map<String, Set<Long>> categoryListingMap = (Map<String, Set<Long>>) cacheProvider
        .get(CATEGORY_LISTING_MAP);
    categoryListingMap.get(categoryName).remove(listing_id);

    // Update Category Map
    Map<String, Category> categoryMap = ((Map<String, Category>) cacheProvider.get(CATEGORY_MAP));
    if(categoryMap.get(categoryName).getCount() > 0){
      topCategoryQueue.remove(categoryMap.get(categoryName));
    }
    categoryMap.get(categoryName).setCount(categoryMap.get(categoryName).getCount()-1);
    topCategoryQueue.add(categoryMap.get(categoryName));
    return true;
  }

  @Override
  public Listing get(long listing_id) {
    return ((Map<Long, Listing>) cacheProvider.get(LISTING_MAP)).get(listing_id);
  }
}
