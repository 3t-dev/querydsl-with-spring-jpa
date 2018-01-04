package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	private CustomerRepositoryQdslImpl repository;
	
	@Bean
	CustomerRepositoryQdslImpl qdslRepository() {
	  return new CustomerRepositoryQdslImpl();
	}
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		System.out.println("anc anc");
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", 20));
			repository.save(new Customer("Jack", "O'Brian", 25));
			repository.save(new Customer("Kim", "Bauer", 20));
			repository.save(new Customer("Jack", "Palmer", 13));
			repository.save(new Customer("Michelle", "Dessler", 14));

			// fetch all customers
			log.info("Customer found with findAll():");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findAll()) {
				log.info(bauer.toString());
			}
			log.info("############################################");
			// fetch all customers with ordering
			log.info("Customer found with findAllWithOrder():");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findAllWithOrder()) {
				log.info(bauer.toString());
			}
			log.info("############################################");
			// fetch by max age
			log.info("Customer found with findByMaxAge():");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByMaxAge()) {
				log.info(bauer.toString());
			}
			log.info("############################################");
			// fetch customers by first name and age less than
			log.info("Customer found with findByFirstNameAndAgeLessThan('Jack, 21'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByFirstNameAndAgeLessThan("Jack", 21)) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
