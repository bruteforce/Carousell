package com.carousell.model;

public class BaseResponse {

  private ErrorCodeAndMessage errorCodeAndMessage;

  public ErrorCodeAndMessage getErrorCodeAndMessage() {
    return errorCodeAndMessage;
  }

  public void setErrorCodeAndMessage(ErrorCodeAndMessage errorCodeAndMessage) {
    this.errorCodeAndMessage = errorCodeAndMessage;
  }
}
