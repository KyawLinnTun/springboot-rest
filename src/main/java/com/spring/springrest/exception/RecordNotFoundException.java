package com.spring.springrest.exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String exception){
      super(exception);
    }
}
