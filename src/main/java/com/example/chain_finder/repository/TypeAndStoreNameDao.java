package com.example.chain_finder.repository;

import com.example.chain_finder.entity.TypeAndStoreName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAndStoreNameDao extends JpaRepository<TypeAndStoreName, TypeAndStoreName.TypeAndStoreNameKey> {

  public boolean existsByStoreName(String storeName);
}
