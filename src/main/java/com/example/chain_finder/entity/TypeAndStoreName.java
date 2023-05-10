package com.example.chain_finder.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "type_and_store_name")
public class TypeAndStoreName {

  @EmbeddedId
  private TypeAndStoreNameKey id;

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

  public TypeAndStoreNameKey getId() {
    return id;
  }

  public void setId(TypeAndStoreNameKey id) {
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

  @Embeddable
  public static class TypeAndStoreNameKey implements Serializable {

    @Column(name = "type") // 店家類型
    private int type;

    @Column(name = "store_name") // 店名
    private String store_name;

    public TypeAndStoreNameKey() {
    }

    public TypeAndStoreNameKey(int type, String store_name) {
      this.type = type;
      this.store_name = store_name;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public String getStore_name() {
      return store_name;
    }

    public void setStore_name(String store_name) {
      this.store_name = store_name;
    }
  }
}
