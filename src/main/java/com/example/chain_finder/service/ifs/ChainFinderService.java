package com.example.chain_finder.service.ifs;

import com.example.chain_finder.controller.*;
import com.example.chain_finder.vo.ChainFinderResponse;

import java.util.List;

public interface ChainFinderService {

  // 新增表格shop_info店家
  // 可多筆新增，錯物的資訊不儲存，僅儲存正確的資訊
  // 表格type_and_store_name無該店家，需要連帶新增
  public ChainFinderResponse addShop(List<AddShopRequest> addShopRequests);

  // 新增表格type_and_store_name店家
  // 可多筆新增，錯物的資訊不儲存，僅儲存正確的資訊
  public ChainFinderResponse addTypeAndStoreNameRequest(List<AddTypeAndStoreNameRequest> addTypeAndStoreNameRequests);


  // 刪除表格shop_info店家，依店名+分店名刪除
  // 可多筆刪除，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  public ChainFinderResponse deleteShop(List<DeleteShopRequest> deleteShopRequests);

  // 刪除表格type_and_store_name店家，依店家類型+店名刪除
  // 可刪除多筆，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  // 刪除成功，連帶表格shop_info店家也會被刪除
  public ChainFinderResponse deleteTypeAndStoreName(List<DeleteTypeAndStoreNameRequest> deleteTypeAndStoreNameRequests);


  // 更新表格shop_info店家
  public ChainFinderResponse updateShop(UpdateShopRequest updateShopRequest);

  // 更新表格type_and_store_name店家
  // 更新成功，連帶表格shop_info店家也會被更新
  public ChainFinderResponse UpdateTypeAndStoreName(UpdateTypeAndStoreNameRequest updateTypeAndStoreNameRequest);
}
