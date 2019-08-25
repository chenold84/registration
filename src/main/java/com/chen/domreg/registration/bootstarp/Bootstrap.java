package com.chen.domreg.registration.bootstarp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.chen.domreg.registration.entity.Domain;
import com.chen.domreg.registration.repository.DomainRepository;

/**
 * Bootstrap class which loads data for the application.
 * @author Chen
 *
 */
@Component
public class Bootstrap implements CommandLineRunner{

	private DomainRepository repository;
	
	public Bootstrap(DomainRepository repository) {
		this.repository = repository;
	}


	@Override
	public void run(String... args) throws Exception {
		List<Domain> domains = new ArrayList<>();
		
		domains.add(new Domain("apple.com.au", true, new BigDecimal(1000)));
		domains.add(new Domain("dict.com", true, new BigDecimal(800)));
		domains.add(new Domain("education.net", true, new BigDecimal(300)));
		
		domains.add(new Domain("com", false, new BigDecimal(10)));
		domains.add(new Domain("net", false, new BigDecimal(9)));
		domains.add(new Domain("com.au", false, new BigDecimal(20)));
		
		repository.saveAll(domains);
	}

}
