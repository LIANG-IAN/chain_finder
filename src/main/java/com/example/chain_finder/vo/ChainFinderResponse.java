package com.example.chain_finder.vo;

import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;

import java.util.List;

public class ChainFinderResponse {

  private List<TypeAndStoreName> id1List;

  private TypeAndStoreName typeAndStoreName;

  private List<ShopInfo> id2List;

  private ShopInfo shopInfo;

  private String message;

  private List<String> messageList;

  public ChainFinderResponse() {
  }

  public ChainFinderResponse(TypeAndStoreName typeAndStoreName, String message) {
    this.typeAndStoreName = typeAndStoreName;
    this.message = message;
  }

  public ChainFinderResponse(ShopInfo shopInfo, String message) {
    this.shopInfo = shopInfo;
    this.message = message;
  }

  public ChainFinderResponse(List<ShopInfo> id2List, List<String> messageList) {
    this.id2List = id2List;
    this.messageList = messageList;
  }

  public ChainFinderResponse(String message) {
    this.message = message;
  }

  public ChainFinderResponse(List<String> messageList) {
    this.messageList = messageList;
  }


  public List<TypeAndStoreName> getId1List() {
    return id1List;
  }

  public TypeAndStoreName getTypeAndStoreName() {
    return typeAndStoreName;
  }

  public List<ShopInfo> getId2List() {
    return id2List;
  }

  public ShopInfo getShopInfo() {
    return shopInfo;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getMessageList() {
    return messageList;
  }
}
