package com.example.chain_finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChainFinderDao extends JpaRepository<ChainFinder, Long> {

  // 搜尋店鋪編號與分店名是否重複
  public boolean existsByStoreIdOrBranchName(long store_id, String branch_name);

  // 批量店鋪編號尋找店鋪
  @Query(value = "select s.store_id from shop s where s.store_id in :storeIds", nativeQuery = true)
  public List<Long> findByStoreIds(@Param("storeIds") List<Long> storeIds);

  // 批量刪除並返回刪除筆數
  @Query(value = "delete from shop where store_id in :storeids", nativeQuery = true)
  int deleteAllByIds(@Param("storeIds") List<Long> storeIds);


}
