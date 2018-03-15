package com.HNS.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.stat;
public interface statRepositories extends CrudRepository<stat, Integer>{
	stat findByStoreId(Integer id);

}


