package com.HNS.Repositories;

import java.util.Vector;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.orderEn;
public interface orderRepositories extends CrudRepository<orderEn, Integer>{
	Vector<orderEn> findByUserId(int userId);
	Vector<orderEn> findByUserIdAndStoreId(int userId,int storeId);
	orderEn findByOrderId(int OrderId);
	Vector<orderEn> findByStoreId(int storeId);
	Vector<orderEn> findByUserIdAndProductId(int userId, int productId);
}