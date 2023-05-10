package com.example.chain_finder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "shop")
public class ChainFinder {

  @Id
  @Column(name = "store_id") // 自訂義店鋪編號 不可重複
  private long storeId;

  @Column(name = "latitude") // 緯度
  private BigDecimal latitude;

  @Column(name = "longitude") // 經度
  private BigDecimal longitude;

  @Column(name = "store_name") // 店名
  private String storeName;

  @Column(name = "branch_name") // 分店名 不可重複
  private String branchName;

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

  public ChainFinder() {
  }

  public long getStoreId() {
    return storeId;
  }

  public void setStoreId(long storeId) {
    this.storeId = storeId;
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
}
