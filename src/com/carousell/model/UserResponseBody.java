package com.carousell.model;

public class UserResponseBody extends BaseResponse{
  private String username;

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    String str = "";
    if(this.getErrorCodeAndMessage() != null){
      str += this.getErrorCodeAndMessage().getMessage();
    }else{
      str += this.username;
    }
    return str;
  }
}
