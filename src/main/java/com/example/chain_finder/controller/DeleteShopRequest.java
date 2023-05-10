package com.example.chain_finder.controller;

import com.example.chain_finder.entity.ShopInfo;

public class DeleteShopRequest {

  private ShopInfo.ShopInfoKey id;

  public DeleteShopRequest() {
  }

  public DeleteShopRequest(ShopInfo.ShopInfoKey id) {
    this.id = id;
  }

  public ShopInfo.ShopInfoKey getId() {
    return id;
  }
}
