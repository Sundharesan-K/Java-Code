package com.secure.spring_secure_6.model;


public class Family {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Family(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
