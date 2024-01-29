package dev.jamtech.ATC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
	}
        
        @GetMapping("/root")
        public String apiIndex()
        {
            return "Hello, this is my string";
        }

}
