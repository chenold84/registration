package com.chen.domreg.registration.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.chen.domreg.registration.entity.Domain;
import com.chen.domreg.registration.repository.DomainRepository;

public class TestRegistrationService {

	@InjectMocks
	RegistrationService service;
	
	@Mock
	DomainRepository repository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDomainDtls() {
		Optional<Domain> domain = Optional.ofNullable(null);
		
		when(repository.findById("a.xxx")).thenReturn(domain);
		
		Optional<Domain> result = service.getDomainDtls("a.xxx");
		
		verify(repository, times(1)).findById("xxx");
		assertFalse("The domain a.xxx should not be present",result.isPresent());
	}
	
	@Test
	public void testDomainDtlsPresent() {
		String domainName = "a.com";
		Domain domainFound = new Domain("com", false, new BigDecimal(10));
		Optional<Domain> domain = Optional.ofNullable(domainFound);
		
		when(repository.findById("com")).thenReturn(domain);
		
		Optional<Domain> result = service.getDomainDtls(domainName);
		
		verify(repository, times(1)).findById(domainName);
		assertTrue("The domain .com should be present",result.isPresent());
	}

	@Test
	public void testGetPremiumDomains() {
		List<Domain> premiumDomains = new ArrayList<>();
		boolean isPremium = true;
		premiumDomains.add(new Domain("apple.com.au", true, new BigDecimal(1000)));
		premiumDomains.add(new Domain("dict.com", true, new BigDecimal(800)));
		premiumDomains.add(new Domain("education.net", true, new BigDecimal(300)));
		
		when(repository.getDomainsBasedOnPremium(isPremium)).thenReturn(premiumDomains);
		
		List<Domain> result = service.getDomainsBasedOnPremium(isPremium);
		assertTrue("The size of the returned list should be 3", result.size() == 3);
	}
	
	@Test
	public void testGetDomains() {
		boolean isPremium = true;
		List<Domain> domains = new ArrayList<>();
		
		domains.add(new Domain("apple.com.au", true, new BigDecimal(1000)));
		domains.add(new Domain("dict.com", true, new BigDecimal(800)));
		domains.add(new Domain("education.net", true, new BigDecimal(300)));
		
		when(repository.getDomainsBasedOnPremium(isPremium)).thenReturn(domains);
		
		List<Domain> result = service.getDomainsBasedOnPremium(isPremium);
		assertTrue("The size of the returned list should be 3", result.size() == 3);
	}
	
}
