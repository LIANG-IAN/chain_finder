package com.example.chain_finder.controller;

import com.example.chain_finder.entity.ShopInfo;

public class DeleteShopRequest {

  private String storeName;

  private String branchName;

  public DeleteShopRequest() {
  }

  public String getStoreName() {
    return storeName;
  }

  public String getBranchName() {
    return branchName;
  }
}
