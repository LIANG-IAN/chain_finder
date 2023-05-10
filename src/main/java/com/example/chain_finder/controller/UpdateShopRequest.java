package com.example.chain_finder.controller;

import com.example.chain_finder.entity.ShopInfo;

public class UpdateShopRequest {

  private ShopInfo.ShopInfoKey id;

  public UpdateShopRequest() {
  }

  public UpdateShopRequest(ShopInfo.ShopInfoKey id) {
    this.id = id;
  }

  public ShopInfo.ShopInfoKey getId() {
    return id;
  }
}
