package com.chen.domreg.registration.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Domain details object with the domain name as the ID field.
 * @author Chen
 *
 */
@Entity
public class Domain {
	
	@Id
	private String domainName;
	private boolean premium;
	private BigDecimal pricePerYear;
	
	public Domain() {}
	
	public Domain(String domainName, boolean premium, BigDecimal pricePerYear) {
		this.domainName = domainName;
		this.premium = premium;
		this.pricePerYear = pricePerYear;
	}
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public BigDecimal getPricePerYear() {
		return pricePerYear;
	}
	public void setPricePerYear(BigDecimal pricePerYear) {
		this.pricePerYear = pricePerYear;
	}

	@Override
	public String toString() {
		return domainName + "," + pricePerYear;
	}
}
