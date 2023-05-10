package com.example.chain_finder.controller;

import com.example.chain_finder.entity.TypeAndStoreName;

public class DeleteTypeAndStoreNameRequest {

  private TypeAndStoreName.TypeAndStoreNameKey id;

  public DeleteTypeAndStoreNameRequest(TypeAndStoreName.TypeAndStoreNameKey id) {
    this.id = id;
  }

  public TypeAndStoreName.TypeAndStoreNameKey getId() {
    return id;
  }

  public void setId(TypeAndStoreName.TypeAndStoreNameKey id) {
    this.id = id;
  }
}
