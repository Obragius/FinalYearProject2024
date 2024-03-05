package dev.jamtech.API;


import java.net.InetAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class AtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
                String ip = InetAddress.getLoopbackAddress().getHostAddress();
                System.out.println(ip);
	}
        
        @GetMapping("/status")
        
        public ResponseEntity<String> apiIndex()
        {
            return new ResponseEntity<String>("Server Online",HttpStatus.OK);
        }

}
