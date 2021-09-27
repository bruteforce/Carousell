package com.carousell.v1.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Listing - A listing of an item put up for sale on the marketplace. Only registered users should
 * be allowed to buy or sell items. - Listings should have the following fields: - Title -
 * Description - Price - Username - Creation time - Each listing can be associated with only 1 user
 * and 1 category.
 */
public class Listing {

  private long id;
  private String title;
  private String description;
  private double price;
  private String creationTime; //epoch time in milliseconds
  private String userName;
  private String categoryName;

  public Listing(String title, String description, double price, String categoryName, String userName) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.categoryName = categoryName;
    this.userName = userName;
    // 2019-02-22 12:34:56
    this.creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public static List<Listing> sortListings(List<Listing> listingsFromCategory, String sortBy, String order) {
    Collections.sort(listingsFromCategory, (l1, l2)->{
      if(sortBy.equals("sort_price")){
        if(order.equals("dsc")){
          return Double.compare(l2.getPrice(), l1.getPrice());
        }else{
          return Double.compare(l1.getPrice(), l2.getPrice());
        }
      }
      else if(sortBy.equals("sort_time")){
        if(order.equals("dsc")){
          return l2.getCreationTime().compareTo(l1.getCreationTime());
        }else{
          return l1.getCreationTime().compareTo(l2.getCreationTime());
        }
      }else{
        return 1;
      }
    });
    return listingsFromCategory;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public String toString() {
//    Phone model 8|Black color, brand new|1000|2019-02-22 12:34:56|Electronics|user1
    return this.getTitle() + "|" + this.getDescription() + "|" + (int)this.getPrice()
        + "|" + this.getCreationTime()+ "|"+this.getCategoryName()+"|"+this.getUserName();
  }
}
