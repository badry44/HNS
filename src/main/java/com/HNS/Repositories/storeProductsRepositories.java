
package com.HNS.Repositories;

import java.util.Vector;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.StoreProducts;

public interface storeProductsRepositories extends CrudRepository<StoreProducts, Integer>{
	StoreProducts findByProductId(int productId);
	Vector <StoreProducts> findByStoreId(int StoreId);
	StoreProducts findByStoreIdAndProductId(int storeId, int productId);
}
