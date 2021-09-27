package com.carousell.model;

public class TopCategoryResponse extends BaseResponse{

  private String topCategory;

  public String getTopCategory() {
    return topCategory;
  }

  public void setTopCategory(String topCategory) {
    this.topCategory = topCategory;
  }

  @Override
  public String toString() {
    return this.topCategory;
  }

}
