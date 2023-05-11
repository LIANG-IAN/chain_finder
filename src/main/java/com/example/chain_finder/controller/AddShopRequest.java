package com.example.chain_finder.controller;

import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;

public class AddShopRequest {

  private ShopInfo shopInfo;

  public AddShopRequest() {
  }

  public ShopInfo getShopInfo() {
    return shopInfo;
  }

  public void setShopInfo(ShopInfo shopInfo) {
    this.shopInfo = shopInfo;
  }
}
