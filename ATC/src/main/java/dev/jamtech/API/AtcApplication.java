package dev.jamtech.API;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dev.jamtech.Model.NavAid;
import dev.jamtech.Model.Points;
import java.io.FileReader;
import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class AtcApplication {
    
        @Autowired
        private MongoTemplate mongoTemplate;
    
    
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
        
        @GetMapping("/loadPoints")
        public ResponseEntity<String> loadPoints()
        {
            try{
                FileReader f = new FileReader("navaids.csv");
                CSVReader r = new CSVReaderBuilder(f).withSkipLines(1).build();
                Points p = new Points();
                for (String[] arr : r.readAll())
                {
                    if ((49 < Double.parseDouble(arr[6])&& Double.parseDouble(arr[6]) < 52) && (-2 < Double.parseDouble(arr[7])&& Double.parseDouble(arr[7]) < 2))
                    {
                        NavAid nav = new NavAid();
                        nav.setName(arr[2]);
                        nav.setxPos(Double.parseDouble(arr[6]));
                        nav.setyPos(Double.parseDouble(arr[7]));
                        
                        p.addPoint(nav);
                    }
                }
                mongoTemplate.insert(p, "NavAids");

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            return new ResponseEntity<String>("Points in database",HttpStatus.OK);
        }

}
