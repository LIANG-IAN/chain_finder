package com.example.chain_finder.service.impl;


import com.example.chain_finder.constants.RtnCode;
import com.example.chain_finder.controller.AddShopRequest;
import com.example.chain_finder.controller.DeleteShopRequest;
import com.example.chain_finder.controller.DeleteTypeAndStoreNameRequest;
import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;
import com.example.chain_finder.repository.ShopInfoDao;
import com.example.chain_finder.repository.TypeAndStoreNameDao;
import com.example.chain_finder.service.ifs.ChainFinderService;
import com.example.chain_finder.vo.ChainFinderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class ChainFinderImpl implements ChainFinderService {

  @Autowired
  TypeAndStoreNameDao typeAndStoreNameDao;

  @Autowired
  ShopInfoDao shopInfoDao;

  @Override
  // 新增店家多筆
  // 多筆新增，錯誤店家資訊不儲存，僅儲存正店家資訊
  public ChainFinderResponse addShop(List<AddShopRequest> addShopRequests) {

    // 用於紀錄目前是第幾筆資料
    int index = 0;
    // 用於儲存錯誤資訊
    List<String> rtnMessages = new ArrayList<>();
    // 用於儲存不重複店家類型與店名之key
    List<TypeAndStoreName> id1List = new ArrayList<>();
    // 用於儲存正確店家資訊之key
    List<ShopInfo> id2List = new ArrayList<>();

    for (AddShopRequest asr : addShopRequests) {

      // 取出TypeAndStoreName類的資料
      // 放入TypeAndStoreNameKey方便後面使用
      TypeAndStoreName.TypeAndStoreNameKey id1 = new TypeAndStoreName.TypeAndStoreNameKey(asr.getType(), asr.getStore_name());

      // 取出ShopInfo類的資料
      // 放入ShopInfoKey方便後面使用
      ShopInfo.ShopInfoKey id2 = new ShopInfo.ShopInfoKey(asr.getStore_name(), asr.getShopInfo().getBranchName());

      // 判斷值是否為空
      if (shopInfoCheck(id1, id2, asr.getShopInfo())) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_INFO_ERROR.getMessage());
        continue;
      }

      // 判斷TypeAndStoreName與ShopInfo之店名是否相同
      // 應相同
      if (!id1.getStore_name().equals(id2.getStoreName())) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        continue;
      }

      // 判斷ShopInfo的「店名與分店名」是否重複
      if (shopInfoDao.existsById(id2)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.DUPLICATE_STORE_ID_OR_BRANCH_NAME_ERROR.getMessage());
        continue;
      }

      // 注意，上方判斷true，下方判斷必為true
      // 但反之上方判斷false，下方判斷未必false

      // 判斷TypeAndStoreName的「店家類型與店名」是否重複
      // 不重複則保存至集合中，下面批次儲存至資料庫
      if (!typeAndStoreNameDao.existsById(id1)) {
        id1List.add(new TypeAndStoreName(id1.getType(), id1.getStore_name()));
      }

      //儲存至ShopInfo集合，下面批次儲存至資料庫
      index++;
      id2List.add(asr.getShopInfo());
      rtnMessages.add("第" + index + "筆資料：" + RtnCode.ADD_SHOP_SUCCESS.getMessage());
    }

    // 注意save是作用在實體對象，而不是key
    typeAndStoreNameDao.saveAll(id1List);
    shopInfoDao.saveAll(id2List);

    return new ChainFinderResponse(id1List, id2List, rtnMessages);
  }

  @Override
  // 刪除shop_info表格店家，依店名+分店名刪除
  // 可多筆刪除，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  public ChainFinderResponse deleteShop(List<DeleteShopRequest> deleteShopRequests) {

    // 用於紀錄目前是第幾筆資料
    int index = 0;
    // 用於儲存錯誤資訊
    List<String> rtnMessages = new ArrayList<>();
    // 用於儲存欲刪除且資訊正確店家
    List<ShopInfo.ShopInfoKey> correctShopKeys = new ArrayList<>();
    // 用於儲存欲刪除但資訊不正確店家
    List<ShopInfo.ShopInfoKey> incorrectShopKeys = new ArrayList<>();

    // 判斷集合是否為空
    if (CollectionUtils.isEmpty(deleteShopRequests)) {
      return new ChainFinderResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }

    for (DeleteShopRequest dsr : deleteShopRequests) {

      // 宣告下方多次使用值之變數
      ShopInfo.ShopInfoKey id = dsr.getId();

      // 判斷是否為空
      if (dsr == null
              || id == null
              || !StringUtils.hasText(id.getStoreName())
              || !StringUtils.hasText(id.getBranchName())) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
        continue;
      }

      // 判斷店家是否存在
      if (!shopInfoDao.existsById(id)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        incorrectShopKeys.add(id);
        continue;
      }

      //將欲刪除店家存入集合，下面批次刪除
      correctShopKeys.add(id);
      index++;
    }

    //注意deleteAllById是作用key
    shopInfoDao.deleteAllById(correctShopKeys);
    rtnMessages.add(RtnCode.DELETE_SHOP_SUCCESS.getMessage() + "共刪除" + correctShopKeys.size() + "筆資料");
    return new ChainFinderResponse(incorrectShopKeys, rtnMessages);
  }

  @Override
  // 刪除type_and_store_name表格店家，依店家類型+店名刪除
  // 可刪除多筆，錯物資訊的店家不刪除，僅刪除正確資訊的店鋪編號
  // 刪除成功，連帶shop_info表格店家也會被刪除
  public ChainFinderResponse deleteTypeAndStoreName(List<DeleteTypeAndStoreNameRequest> deleteTypeAndStoreNameRequests) {

    // 用於紀錄目前是第幾筆資料
    int index = 0;
    // 用於儲存錯誤資訊
    List<String> rtnMessages = new ArrayList<>();
    // 用於儲存欲從表格type_and_store_name刪除且資訊正確店家
    List<TypeAndStoreName.TypeAndStoreNameKey> correctShopKeys = new ArrayList<>();
    // 用於儲存欲從表格type_and_store_name刪除但資訊不正確店家
    List<TypeAndStoreName.TypeAndStoreNameKey> incorrectShopKeys = new ArrayList<>();
    //用於儲存欲從表格shop_info刪除且資訊正確店家
    List<String> storeNames = new ArrayList<>();


    // 判斷集合是否為空
    if (CollectionUtils.isEmpty(deleteTypeAndStoreNameRequests)) {
      return new ChainFinderResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }

    for (DeleteTypeAndStoreNameRequest dtasr : deleteTypeAndStoreNameRequests) {

      // 宣告下方多次使用值之變數
      TypeAndStoreName.TypeAndStoreNameKey id = dtasr.getId();
      String storeName = id.getStore_name();
      int type = id.getType();


      // 判斷是否為空
      if (dtasr == null
              || id == null
              || type < 1
              || type > 3
              || !StringUtils.hasText(storeName)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_INFO_ERROR.getMessage());
        incorrectShopKeys.add(id);
        continue;
      }

      // 判斷店家是否存在
      if (!typeAndStoreNameDao.existsById(id)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        incorrectShopKeys.add(id);
        continue;
      }

      //將欲從表格type_and_store_name刪除店家存入集合，下面批次刪除
      correctShopKeys.add(id);
      index++;

      //將欲從表格shop_info刪除店家存入集合，下面批次刪除
      storeNames.add(storeName);
    }

    // 批次刪除表格shop_info店家，使用SQL語法deleteAllByIds方法
    // 這裡省略判斷店家是否存在表格shop_info，因為不存在就不會執行刪除
    int deleteCount = shopInfoDao.deleteAllByIds(storeNames);
    // 批次刪除表格type_and_store_name店家
    typeAndStoreNameDao.deleteAllById(correctShopKeys);

    rtnMessages.add(RtnCode.DELETE_SHOP_SUCCESS.getMessage() + "共刪除" + correctShopKeys.size() + "筆資料");
    return new ChainFinderResponse(incorrectShopKeys, rtnMessages);
  }

  @Override
  // 更新shop_info表格店家
  public ChainFinderResponse updateShop(ShopInfo newShop) {

    // 判斷值是否為空
    if (shopInfoCheck2(newShop)) {
      return new ChainFinderResponse(RtnCode.SHOP_INFO_ERROR.getMessage());
    }

    // 取得表格shop_info內該店家的資訊
    // 判斷店家是否存在
    Optional<ShopInfo> optionalShop = shopInfoDao.findById(newShop.getId());
    if (!optionalShop.isPresent()){
      return new ChainFinderResponse(RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
    }
    ShopInfo oldShop = optionalShop.get();

    // 判斷店名是否更新
    // 如有更新需再判斷表格type_and_store_name內有沒有對應店家
    // 無對應店家則要求先新增表格type_and_store_name的店家資訊
    if (!oldShop.getStoreName().equals(newShop.getStoreName())){
      if(!typeAndStoreNameDao.existsByStoreName(newShop.getStoreName())){
        return new ChainFinderResponse(RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
      }
    }


  }


  // 值為空反回true
  private boolean shopInfoCheck(TypeAndStoreName.TypeAndStoreNameKey item1, ShopInfo.ShopInfoKey item2, ShopInfo
          shopInfo) {
    return item1 == null
            || item1.getType() < 1
            && item1.getType() > 3
            || !StringUtils.hasText(item1.getStore_name())
            || shopInfoCheck2(shopInfo);
  }

  // 值為空反回true
  private boolean shopInfoCheck2(ShopInfo shop) {
    return shop == null
            || !StringUtils.hasText(shop.getStoreName())
            || !StringUtils.hasText(shop.getBranchName())
            || shop.getAddress() == null
            || shop.getBusinessHours() == null
            || shop.getDayOff() == null
            || shop.getTel() == null
            || shop.getUrl() == null;
  }

}

