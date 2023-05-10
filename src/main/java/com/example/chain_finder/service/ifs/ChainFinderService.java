package com.example.chain_finder.service.ifs;

import com.example.chain_finder.controller.AddShopRequest;
import com.example.chain_finder.controller.DeleteShopRequest;
import com.example.chain_finder.controller.DeleteTypeAndStoreNameRequest;
import com.example.chain_finder.entity.ChainFinder;
import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;
import com.example.chain_finder.vo.ChainFinderResponse;

import java.util.List;

public interface ChainFinderService {

  // 新增店家
  // 可多筆新增，錯物的資訊不儲存，僅儲存正確的資訊
  public ChainFinderResponse addShop(List<AddShopRequest> addShopRequests);

  // 刪除shop_info表格店家，依店名+分店名刪除
  // 可多筆刪除，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  public ChainFinderResponse deleteShop(List<DeleteShopRequest> deleteShopRequests);

  // 刪除type_and_store_name表格店家，依店家類型+店名刪除
  // 可刪除多筆，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  // 刪除成功，連帶shop_info表格店家也會被刪除
  public ChainFinderResponse deleteTypeAndStoreName(List<DeleteTypeAndStoreNameRequest> deleteTypeAndStoreNameRequests);


  // 更新shop_info表格店家
  public ChainFinderResponse updateShop(ShopInfo newShop);

  // 更新type_and_store_name表格店家
  // 更新成功，連帶shop_info表格店家也會被更新

}
