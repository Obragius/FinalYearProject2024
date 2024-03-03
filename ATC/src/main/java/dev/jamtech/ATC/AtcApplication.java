package dev.jamtech.ATC;


import java.net.InetAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AtcApplication {

    
	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
                String ip = InetAddress.getLoopbackAddress().getHostAddress();
                System.out.println(ip);
	}
        
        @GetMapping("/root")
        
        public ResponseEntity<String> apiIndex()
        {
            return new ResponseEntity<String>("hello",HttpStatus.OK);
        }

}
