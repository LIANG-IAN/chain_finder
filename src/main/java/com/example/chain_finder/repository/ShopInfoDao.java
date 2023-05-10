package com.example.chain_finder.repository;

import com.example.chain_finder.entity.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShopInfoDao extends JpaRepository<ShopInfo, ShopInfo.ShopInfoKey> {

  // 批量刪除並返回刪除筆數
  @Transactional
  @Modifying
  @Query(value = "delete from shop_info s where s.store_name in :storeName", nativeQuery = true)
  int deleteAllByIds(@Param("storeName") List<String> storeName);

}
