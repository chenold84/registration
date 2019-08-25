package com.chen.domreg.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chen.domreg.registration.entity.Domain;
import com.chen.domreg.registration.service.RegistrationService;

/**
 * Main application
 * @author Chen
 *
 */
@SpringBootApplication
public class RegistrationApplication {
	
	private static RegistrationService service;
	
	public RegistrationApplication(RegistrationService service) {
		RegistrationApplication.service = service;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
		
		printNonPremiumDomains();
		printPremiumDomains();
		getQuoteForConsumerInput();
	}

	/**
	 * Prints the domains which are premium.
	 */
	private static void printPremiumDomains() {
		System.out.println("\nDomain registrar’s price list (per year) of premium domain registrations");
		System.out.println("=========================\n");
		printDomainInfo(true);
	}

	/**
	 * Prints the normal domains
	 */
	private static void printNonPremiumDomains() {
		System.out.println("\nDomain registrar’s price list (per year) of domain registrations per in a zone");
		System.out.println("=========================\n");
		printDomainInfo(false);
	}
	
	private static void printDomainInfo(boolean isPremium) {
		List<Domain> premiumDomains = service.getDomainsBasedOnPremium(isPremium);
		
		premiumDomains.forEach(domain -> {
			System.out.println(domain);
		});
	}
	
	/**
	 * Gets user request for domain names and prints the price for it
	 */
	private static void getQuoteForConsumerInput() {
		BigDecimal totalPrice = new BigDecimal(0);
		HashMap<String, Integer> request = new HashMap<>();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nConsumer’s list of domain registration request");
		System.out.println("=========================\n");
		
		while(true) {
			try {
				String input = bufferedReader.readLine().trim();
				
				if(input.length() == 0) { /* Break the loop if the user enters a blank line. */
					break;
				}else if(input.split(",").length != 2){
					System.out.println("Format should be <Domain Name>,<Quantity>");
					continue;
				}
				String domainName = input.split(",")[0].trim();
				Integer qty = Integer.parseInt(input.split(",")[1].trim());
				
				if(request.containsKey(domainName)) { //Check if the request for this domain is already present.
					System.out.println("Duplicate request");
				}else if(!(service.getDomainDtls(domainName)).isPresent()){ //Check if the domain name is present.
					System.out.println("Invalid domain name");
				}else {
					request.put(domainName, qty);
				}
			} catch (IOException e) {
				System.out.println("Unable to read the input: "+ e.getMessage());
			}catch (NumberFormatException n) {
				System.out.println("Quantity should be numeric");
			}
		}
		//Get the price for the user request and print it.
		totalPrice = service.getPrice(request);
		System.out.println("Total price for the requested domain(s) is: $"+totalPrice.toString());
	}
}
