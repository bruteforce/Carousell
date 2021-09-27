package com.carousell.v1.model;

/*
  Category
  - Groupings of listings of the same "category". E.g. Electronics, Fashion etc
  - Category reads can be sorted on Price or creation time.
 */
public class Category {
  private String type;
  private int count;

  public Category(String type, int count) {
    this.type = type;
    this.count = count;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

}
