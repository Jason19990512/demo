package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.CoinDeskMain;

public interface CoinDeskMainRepository extends CrudRepository<CoinDeskMain, Integer>{

//	@Modifying
//    @Transactional
//	@Query()
//	void handleOldData();
	
	@Query(value ="SELECT * FROM COINDESK_MAIN c ORDER BY c.ID DESC LIMIT 1",nativeQuery = true)
	CoinDeskMain findLastOne();
}
