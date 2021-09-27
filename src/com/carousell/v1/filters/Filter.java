package com.carousell.v1.filters;


public interface Filter {
  void doFilter(Object request) throws Exception;
}
