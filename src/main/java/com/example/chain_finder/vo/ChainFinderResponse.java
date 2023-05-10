package com.example.chain_finder.vo;

import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;

import java.util.List;

public class ChainFinderResponse {

  private List<TypeAndStoreName> id1List;

  private List <TypeAndStoreName.TypeAndStoreNameKey> id1KeyList;

  private List<ShopInfo> id2List;

  private List<ShopInfo.ShopInfoKey> id2KeyList;

  private String message;

  private List<String> messageList;

  public ChainFinderResponse() {
  }

  public ChainFinderResponse(List<TypeAndStoreName> id1List, List<ShopInfo> id2List, List<String> messageList) {
    this.id1List = id1List;
    this.id2List = id2List;
    this.messageList = messageList;
  }

  public ChainFinderResponse(List<TypeAndStoreName.TypeAndStoreNameKey> id1KeyList, List<String> messageList) {
    this.id1KeyList = id1KeyList;
    this.messageList = messageList;
  }

  public ChainFinderResponse(String message) {
    this.message = message;
  }

  public ChainFinderResponse(List<String> messageList) {
    this.messageList = messageList;
  }

  public ChainFinderResponse(List<ShopInfo.ShopInfoKey> id2KeyList, List<String> messageList) {
    this.id2KeyList = id2KeyList;
    this.messageList = messageList;
  }

  public List<TypeAndStoreName> getId1List() {
    return id1List;
  }

  public List<TypeAndStoreName.TypeAndStoreNameKey> getId1KeyList() {
    return id1KeyList;
  }

  public List<ShopInfo> getId2List() {
    return id2List;
  }

  public List<ShopInfo.ShopInfoKey> getId2KeyList() {
    return id2KeyList;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getMessageList() {
    return messageList;
  }
}
