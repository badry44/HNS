
package com.HNS.Repositories;
import java.util.Vector;

import org.springframework.data.repository.CrudRepository;

import com.HNS.Entity.collaborators;
public interface collaboratorsRepositories extends CrudRepository<collaborators, Integer>{

	Vector <collaborators> findByColloaboratorUserId(int UserId);

}


