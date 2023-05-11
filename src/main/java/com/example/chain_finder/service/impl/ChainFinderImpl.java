package com.example.chain_finder.service.impl;


import com.example.chain_finder.constants.RtnCode;
import com.example.chain_finder.controller.*;
import com.example.chain_finder.entity.ShopInfo;
import com.example.chain_finder.entity.TypeAndStoreName;
import com.example.chain_finder.repository.ShopInfoDao;
import com.example.chain_finder.repository.TypeAndStoreNameDao;
import com.example.chain_finder.service.ifs.ChainFinderService;
import com.example.chain_finder.vo.ChainFinderResponse;
import jakarta.persistence.Column;
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
  // 新增shop_info表格店家
  // 可多筆新增，錯物的資訊不儲存，僅儲存正確的資訊
  // type_and_store_name表格無該店家，需要連帶新增
  public ChainFinderResponse addShop(List<AddShopRequest> addShopRequests) {

    // 用於紀錄目前是第幾筆資料
    int index = 0;
    // 用於儲存錯誤資訊
    List<String> rtnMessages = new ArrayList<>();
    // 用於儲存正確店家資訊
    List<ShopInfo> shopInfos = new ArrayList<>();

    for (AddShopRequest asr : addShopRequests) {

      // 取出ShopInfo
      ShopInfo shopInfo = asr.getShopInfo();

      // 判斷值是否為空
      if (shopInfoCheck(shopInfo)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_INFO_ERROR.getMessage());
        continue;
      }

      // 判斷ShopInfo表格的「店名與分店名」是否有重複
      if (shopInfoDao.existsByStoreNameAndBranchName(shopInfo.getStoreName(), shopInfo.getBranchName())) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.DUPLICATE_STORE_ID_OR_BRANCH_NAME_ERROR.getMessage());
        continue;
      }

      // 判斷TypeAndStoreName表格的「店名」是否有該店名
      // 沒有則需要先新增才允許新增判斷ShopInfo的資料
      // 因需要決定「店家類型」，所以無法該方法新增TypeAndStoreName的資料
      if (!typeAndStoreNameDao.existsByStoreName(shopInfo.getStoreName())) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        continue;
      }

      //儲存至ShopInfo集合，下面批次儲存至資料庫
      index++;
      shopInfos.add(asr.getShopInfo());
      rtnMessages.add("第" + index + "筆資料：" + RtnCode.ADD_SHOP_SUCCESS.getMessage());
    }

    // 注意save是作用在實體對象，而不是id
    shopInfoDao.saveAll(shopInfos);

    return new ChainFinderResponse(shopInfos, rtnMessages);
  }

  @Override
  // 新增type_and_store_name表格店家
  // 可多筆新增，錯物的資訊不儲存，僅儲存正確的資訊
  public ChainFinderResponse addTypeAndStoreNameRequest(List<AddTypeAndStoreNameRequest> addTypeAndStoreNameRequests){

    // 用於紀錄目前是第幾筆資料
    int index = 0;
    // 用於儲存錯誤資訊
    List<String> rtnMessages = new ArrayList<>();
    // 用於儲存欲刪除且資訊正確店家
    List<Integer> shopIds = new ArrayList<>();

    // 判斷集合是否為空
    if (CollectionUtils.isEmpty(addTypeAndStoreNameRequests)) {
      return new ChainFinderResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }

    for (AddTypeAndStoreNameRequest atasnr : addTypeAndStoreNameRequests) {

      // 宣告常用變數
      int type = atasnr.getTypeAndStoreName().getType();
      String storeName = atasnr.getTypeAndStoreName().getStoreName();

      // 判斷值是否為空
      if (type > 3
              || type <1
              || !StringUtils.hasText(storeName)){
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_INFO_ERROR.getMessage());
        continue;
      }

      // 判斷TypeAndStoreName表格的「店名與分店名」是否有重複


    }
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
    List<Integer> shopIds = new ArrayList<>();

    // 判斷集合是否為空
    if (CollectionUtils.isEmpty(deleteShopRequests)) {
      return new ChainFinderResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }

    for (DeleteShopRequest dsr : deleteShopRequests) {

      // 宣告常用變數
      String storeName = dsr.getStoreName();
      String branchName = dsr.getBranchName();
      // 判斷是否為空
      if (dsr == null
              || !StringUtils.hasText(storeName)
              || !StringUtils.hasText(branchName)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
        continue;
      }

      // 判斷店家是否存在。不使用.exists()因為下面批次刪除需要id
      ShopInfo shopInfo = shopInfoDao.findByStoreNameAndBranchName(storeName, branchName);
      if (shopInfo == null) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        continue;
      }

      //將欲刪除店家存入集合，下面批次刪除
      shopIds.add(shopInfo.getId());
      index++;
    }

    //注意deleteAllById是作用id
    shopInfoDao.deleteAllById(shopIds);
    rtnMessages.add(RtnCode.DELETE_SHOP_SUCCESS.getMessage() + "共刪除" + shopIds.size() + "筆資料");
    return new ChainFinderResponse(rtnMessages);
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
    List<Integer> shopIds = new ArrayList<>();
    //用於儲存欲從表格shop_info刪除且資訊正確店家
    List<String> storeNames = new ArrayList<>();


    // 判斷集合是否為空
    if (CollectionUtils.isEmpty(deleteTypeAndStoreNameRequests)) {
      return new ChainFinderResponse(RtnCode.INPUT_NOT_ALLOWED_BLANK_ERROR.getMessage());
    }

    for (DeleteTypeAndStoreNameRequest dtasr : deleteTypeAndStoreNameRequests) {

      // 宣告下方多次使用值之變數
      int type = dtasr.getType();
      String storeName = dtasr.getStoreName();

      // 判斷是否為空
      if (dtasr == null
              || type < 1
              || type > 3
              || !StringUtils.hasText(storeName)) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_INFO_ERROR.getMessage());
        continue;
      }

      // 判斷店家是否存在。不使用.exists()因為下面批次刪除需要id
      TypeAndStoreName typeAndStoreName = typeAndStoreNameDao.findByTypeAndStoreName(type, storeName);
      if (typeAndStoreName == null) {
        index++;
        rtnMessages.add("第" + index + "筆資料：" + RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
        continue;
      }

      //將欲從表格type_and_store_name刪除店家存入集合，下面批次刪除
      shopIds.add(typeAndStoreName.getId());
      index++;

      //將欲從表格shop_info刪除店家存入集合，下面批次刪除
      storeNames.add(storeName);
    }

    // 批次刪除表格shop_info店家，使用SQL語法deleteAllByIds方法
    // 這裡省略判斷店家是否存在表格shop_info，因為不存在就不會執行刪除
    shopInfoDao.deleteAllByStoreNames(storeNames);
    // 批次刪除表格type_and_store_name店家
    typeAndStoreNameDao.deleteAllById(shopIds);

    rtnMessages.add(RtnCode.DELETE_SHOP_SUCCESS.getMessage() + "共刪除" + shopIds.size() + "筆資料");
    return new ChainFinderResponse(rtnMessages);
  }

  @Override
  // 更新shop_info表格店家
  public ChainFinderResponse updateShop(UpdateShopRequest updateShopRequest) {

    // 取出ShopInfo
    ShopInfo newShop = updateShopRequest.getShopInfo();
    // 宣告常用變數
    String storeName = newShop.getStoreName();
    String branchName = newShop.getBranchName();

    // 判斷值是否為空
    if (shopInfoCheck(newShop)) {
      return new ChainFinderResponse(RtnCode.SHOP_INFO_ERROR.getMessage());
    }

    // 取得表格shop_info內該店家的資訊
    // 判斷店家是否存在
    Optional<ShopInfo> opShop = shopInfoDao.findById(newShop.getId());
    if (!opShop.isPresent()) {
      return new ChainFinderResponse(RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
    }
    ShopInfo oldShop = opShop.get();

    // 判斷店名是否更新
    // 如要更新，則再判斷表格type_and_store_name內有沒有對應店家
    // 無對應店家則要求先新增表格type_and_store_name的店家資訊
    if (!oldShop.getStoreName().equals(newShop.getStoreName())) {
      if (!typeAndStoreNameDao.existsByStoreName(newShop.getStoreName())) {
        return new ChainFinderResponse(RtnCode.SHOP_NOT_FOUND_ERROR.getMessage());
      }
    }

    // 使用Copy Constructor
    ShopInfo copyShop = new ShopInfo(newShop);
    shopInfoDao.save(copyShop);
    return new ChainFinderResponse(copyShop,RtnCode.UPDATE_SHOP_SUCCESS.getMessage());
  }



  // 值為空反回true
  private boolean shopInfoCheck(ShopInfo shop) {
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

