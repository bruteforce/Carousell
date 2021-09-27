package com.carousell.v1.model;

/*
 * User
 * - Anyone who uses the marketplace
 * - Identified by username, which should be unique throughout the platform, but
 * case insensitive.
 * - Each user can create any number of listings having any category.
 */
public class User {
  private String username; //case insensitive

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
