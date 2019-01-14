package br.com.eptv.redegeral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication 
public class RedeGeralApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RedeGeralApplication.class, args);
	
		String encode = new BCryptPasswordEncoder().encode("eptv");
		System.out.println(encode);
		
	}
	
}
