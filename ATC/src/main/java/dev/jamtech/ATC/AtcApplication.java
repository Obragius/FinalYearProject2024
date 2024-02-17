package dev.jamtech.ATC;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
public class AtcApplication {

    
	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
	}
        
        @GetMapping("/root")
        
        public ResponseEntity<String> apiIndex()
        {
            return new ResponseEntity<String>("hello",HttpStatus.OK);
        }

}
