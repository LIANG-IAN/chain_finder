package com.example.chain_finder.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "type_and_store_name")
public class TypeAndStoreName {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "type") // 店家類型
  private int type;

  @Column(name = "store_name") // 店名
  private String storeName;

  public TypeAndStoreName() {
  }

  public TypeAndStoreName(int type, String storeName) {
    this.type = type;
    this.storeName = storeName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String store_name) {
    this.storeName = store_name;
  }

}
