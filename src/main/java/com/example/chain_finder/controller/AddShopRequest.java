package com.example.chain_finder.controller;

import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;

public class AddShopRequest {

  // 不是TypeAndStoreName類參數，而是type、store_name，
  // 是因為之後方法內判斷存在與否時，需再結合成TypeAndStoreNameKey
  private int type;

  private String store_name;

  private ShopInfo shopInfo;

  public AddShopRequest() {
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

  public ShopInfo getShopInfo() {
    return shopInfo;
  }

  public void setShopInfo(ShopInfo shopInfo) {
    this.shopInfo = shopInfo;
  }
}
