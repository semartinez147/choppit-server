package com.semartinez.choppit.model.controller.exception;

public class ConnectionFailureException extends RuntimeException {

  public ConnectionFailureException() {
    super("Please check your internet connection and try again.");
  }

  public ConnectionFailureException(String s) {
    super(s);
  }
}
