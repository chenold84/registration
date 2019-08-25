package com.chen.domreg.registration.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chen.domreg.registration.entity.Domain;
import com.chen.domreg.registration.repository.DomainRepository;

/**
 * Domain registration operations.
 * @author Chen
 *
 */
@Service
public class RegistrationService {

	private DomainRepository repository;
	
	public RegistrationService(DomainRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Gets a set of domain names and the quantity as the request and returns
	 * the Price for the set.
	 * @param request
	 * @return
	 */
	public BigDecimal getPrice(HashMap<String, Integer> request){
		BigDecimal totalPrice = new BigDecimal(0);
		
		for(String domainName : request.keySet()) {
			Optional<Domain> domain = getDomainDtls(domainName);
			
			if(domain.isPresent()) {
				BigDecimal price = domain.get().getPricePerYear().
							multiply(new BigDecimal(request.get(domainName)));
				totalPrice = totalPrice.add(price);
			}			
		}
		
		return totalPrice;
	}
	
	/**
	 * Returns the domain details for the given domain name.
	 * @param domainName
	 * @return
	 */
	public Optional<Domain> getDomainDtls(String domainName) {
		String[] domArr = domainName.split("-");
		Optional<Domain> domain = repository.findById(domArr[domArr.length - 1]);
		return domain;
	}

	/**
	 * Takes the premium flag as input and returns the list of domains 
	 * based on it
	 * @param isPremium
	 * @return
	 */
	public List<Domain> getDomainsBasedOnPremium(boolean isPremium) {		
		return repository.getDomainsBasedOnPremium(isPremium);
	}
}
