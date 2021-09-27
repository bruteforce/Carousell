package com.carousell.model;

public class DeleteListingResponse extends BaseResponse{

  private String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    if(this.getErrorCodeAndMessage() == null)
      return this.status;
    else
      return this.getErrorCodeAndMessage().getMessage();
  }

}
