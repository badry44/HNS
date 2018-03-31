package com.HNS.Repositories;
import java.util.Vector;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.products;
public interface productsRepositories extends CrudRepository<products, Integer>{
	Vector <products> findAll();
	products findByProductId(int id);
}

