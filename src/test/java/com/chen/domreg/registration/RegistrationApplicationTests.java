package com.chen.domreg.registration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chen.domreg.registration.bootstarp.Bootstrap;
import com.chen.domreg.registration.entity.Domain;
import com.chen.domreg.registration.repository.DomainRepository;
import com.chen.domreg.registration.service.RegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationApplicationTests {

	@Autowired
	DomainRepository repository;
	
	RegistrationService service;
	
	@Before
	public void setUp() throws Exception {
		Bootstrap bootstrap = new Bootstrap(repository);
		bootstrap.run();
		service = new RegistrationService(repository);
	}
	
	@Test
	public void TestGetDomainDtls() {
		Optional<Domain> domain;
		
		String domainName = "a.com";
		
		domain = service.getDomainDtls(domainName);
		
		assertTrue("Unable to find .com", domain.isPresent());
		
		domainName = "a.xxx";
		domain = service.getDomainDtls(domainName);
		assertFalse(".xxx should not return true", domain.isPresent());
	}
	
	@Test
	public void TestGetPrice() {
		HashMap<String, Integer> request = new HashMap<>();
		request.put("a.com", 2);
		request.put("apple.com.au", 2);
		request.put(".xxx", 2);
		
		BigDecimal result = service.getPrice(request);
		assertTrue("The price for the request should be 2020", result.intValue() == 2020);
	}
	
	@Test
	public void TestGetDomainsBasedOnPremium() {
		List<Domain> domains = service.getDomainsBasedOnPremium(true);
		assertTrue("The total number of premium domains should be 3", domains.size() == 3);
		
		domains = service.getDomainsBasedOnPremium(false);
		assertTrue("The total number of domains should be 3", domains.size() == 3);
	}
}
