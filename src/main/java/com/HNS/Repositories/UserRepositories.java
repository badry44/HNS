package com.HNS.Repositories;
import java.util.Vector;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.User;
public interface UserRepositories extends CrudRepository<User, Integer>{
	Vector<User> findByUserName(String us);
	User findByIdAndId(int id,int id1);

}


