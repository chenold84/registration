package com.chen.domreg.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chen.domreg.registration.entity.Domain;

/**
 * Repository for CRUD operations on Domain entity.
 * @author Chen
 *
 */
@Repository
public interface DomainRepository extends CrudRepository<Domain, String>{
	
	/**
	 * Returns the list of domains based on the premium flag.
	 * @param isPremium
	 * @return
	 */
	@Query("SELECT d FROM Domain d where premium = :isPremium")
	List<Domain> getDomainsBasedOnPremium(@Param(value = "isPremium") boolean isPremium);

}
