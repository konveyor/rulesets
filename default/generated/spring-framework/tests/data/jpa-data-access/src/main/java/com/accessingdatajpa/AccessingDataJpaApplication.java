package com.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.CannotSerializeTransactionException;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			repository.save(new Customer("CannotSerializeTransaction", "Exception"));
			repository.save(new Customer("DeadlockLoserDataAccess", "Exception"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
				try{
					throwException("CannotSerializeTransaction", customer);
				}catch(CannotSerializeTransactionException e){
					log.error("CannotSerializeTransaction Exception thrown and caught");
				}
				try{
					throwException("DeadlockLoserDataAccess", customer);
				}catch(CannotSerializeTransactionException e){
					log.error("CannotSerializeTransaction Exception thrown and caught");
				}
			});
			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}

	public void throwException(String exception, Customer customer){
		if(customer.isException(exception)){
			throw new CannotSerializeTransactionException("Exception");
		}
		else if(customer.isException(exception)){
			throw new DeadlockLoserDataAccessException("Exception", new Throwable());
		}
	}
}
