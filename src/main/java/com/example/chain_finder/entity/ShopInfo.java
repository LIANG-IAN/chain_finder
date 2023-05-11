package com.example.chain_finder.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shop_info")
public class ShopInfo {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "store_name") // 店名
  private String storeName;

  @Column(name = "branch_name") // 分店名 不可重複
  private String branchName;

  @Column(name = "latitude") // 緯度
  private BigDecimal latitude;

  @Column(name = "longitude") // 經度
  private BigDecimal longitude;

  @Column(name = "address")
  private String address;

  @Column(name = "business_hours") // 營業時間
  private String businessHours = "";

  @Column(name = "day_off") // 定休日
  private String dayOff = "";

  @Column(name = "tel")
  private String tel = "";

  @Column(name = "url") //店鋪或分店網址
  private String url = "";

  public ShopInfo() {
  }

  public ShopInfo(String storeName, String branchName) {
    this.storeName = storeName;
    this.branchName = branchName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBusinessHours() {
    return businessHours;
  }

  public void setBusinessHours(String businessHours) {
    this.businessHours = businessHours;
  }

  public String getDayOff() {
    return dayOff;
  }

  public void setDayOff(String dayOff) {
    this.dayOff = dayOff;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // Copy Constructor
  public ShopInfo(ShopInfo other) {
    this.id = other.id;
    this.storeName = other.storeName;
    this.branchName = other.branchName;
    this.latitude = other.latitude;
    this.longitude = other.longitude;
    this.address = other.address;
    this.businessHours = other.businessHours;
    this.dayOff = other.dayOff;
    this.tel = other.tel;
    this.url = other.url;
  }
}
