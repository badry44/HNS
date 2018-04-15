package com.HNS.Repositories;
import java.util.Vector;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.stores;

public interface storesRepositories extends CrudRepository<stores, Integer>{

	Vector <stores> findByStoreOwnerAndStoreState(int StoreOwner,int stat);
	Vector <stores> findByStoreState(int state);
	stores findByStoreId(Integer id);
	Vector <stores> findByStoreOwner(int userId);

/*@Modifying(clearAutomatically = true)
@Query("update stores u set u.storeState = 2 where u.storeId= ?1")
void updateBystoreId(Integer StoreId);*/


}



