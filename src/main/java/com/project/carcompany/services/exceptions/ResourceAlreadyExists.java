package com.project.carcompany.services.exceptions;

public class ResourceAlreadyExists extends RuntimeException{
  
  public ResourceAlreadyExists(Object o) {
    super("Resource " + o + " already exists");
  }

}
