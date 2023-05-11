package com.example.chain_finder.constants;

public enum RtnCode {

  ADD_SHOP_SUCCESS("200", "新增店家成功"),
  ADD_STUDENT_SUCCESS("200", "新增學生成功"),
  PARTIAL_DELETION_SUCCESS("200", "部分刪除成功"),
  DELETE_SHOP_SUCCESS("200", "店家刪除成功"),
  DROP_COURSE_SUCCESS("200", "退選成功"),
  FIND_SUCCESS("200", "尋找成功"),
  GET_COURSE_INFO_SUCCESS("200", "取得課程資訊成功"),
  SELECT_COURSE_SUCCESS("200", "選修成功"),
  UPDATE_SHOP_SUCCESS("200", "更新店家成功"),
  CANNOT_DELETE_COURSE_ERROR("400", "仍有學生選修該課程，無法刪除"),
  SHOP_INFO_ERROR("400", "輸入店家資訊錯誤"),
  COURSE_FULL_ERROR("400", "該課程已滿"),
  SHOP_NOT_FOUND_ERROR("404", "找不到該店家"),
  CREDIT_OVER_LIMIT_ERROR("400", "無法加選，學分超過"),
  DUPLICATE_STORE_NAME_OR_BRANCH_NAME_ERROR("400", "店家名與分店名重複"),
  DUPLICATE_STORE_NAME_ERROR("400", "店家名重複"),
  DUPLICATE_COURSE_TIME_ERROR("400", "已選修同時段課程"),
  DUPLICATE_STUDENT_ID_ERROR("400", "已有相同ID學生註冊"),
  INCORRECT_COURSE_INFO_ERROR("400", "設定課程資訊不正確"),
  INPUT_NOT_ALLOWED_BLANK_ERROR("400", "輸入不得為空白"),
  NO_COURSE_FOUND_ERROR("400", "無找到課程"),
  NO_SAME_SHOP_INFO_ERROR("404", "無相同名稱店家"),
  NOT_SELECTED_ERROR("400", "您沒有選修該門課"),
  STUDENT_NOT_EXIST_ERROR("400", "學生不存在");



  private String code;

  private String message;


  RtnCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
