package com.project.carcompany.dto;

import java.io.Serializable;

public class CarDTO implements Serializable{
  
  private String name;
  private String company;
  private Double price;
  private String imageUrl;
  private Long[] categoriesIds;

  public CarDTO(){
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Long[] getCategoriesIds() {
    return categoriesIds;
  }
}
