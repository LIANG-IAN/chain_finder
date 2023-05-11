package com.example.chain_finder;

import com.example.chain_finder.repository.ChainFinderDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ChainFinderApplicationTests {

  @Autowired
  ChainFinderDao chainFinderDao;

  @Test
  public void AddShop() {
    ChainFinder temp = new ChainFinder();
    temp.setStoreId(465662);
    temp.setBranchName("test");
    temp.setStoreName("test");
    BigDecimal bd1 = new BigDecimal("10");
    temp.setLatitude(bd1);
    temp.setLongitude(bd1);
    temp.setAddress("test");
    chainFinderDao.save(temp);

  }



}
