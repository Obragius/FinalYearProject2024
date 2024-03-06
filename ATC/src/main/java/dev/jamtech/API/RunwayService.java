/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.GeoMap;
import dev.jamtech.Model.GeographicalCalculator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("api/runway")
@Service
public class RunwayService {
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List> tickMap(@RequestBody Map payload) throws IOException
    {
        String AirportCode = (String)payload.get("airport");
        int runway = Integer.parseInt((String)payload.get("runway"));
        Document myDoc = Jsoup.connect("https://nats-uk.ead-it.com/cms-nats/opencms/en/Publications/AIP/Current-AIRAC/html/eAIP/EG-AD-2."+AirportCode+"-en-GB.html").get();
        String pattern = "°";
        // Setup regex 
        Pattern myPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        String textToAnalyse = myDoc.getElementById(AirportCode+"-AD-2.12").text();
        Matcher myMatcher = myPattern.matcher(textToAnalyse);
        List<MatchResult> myList = myMatcher.results().toList();
        List<String> myNums = new ArrayList();
        for (MatchResult match : myList)
        {
            // Get the runway bearings
            int degreeIndex = match.start();
            int numIndex = degreeIndex - 6;
            myNums.add(textToAnalyse.substring(numIndex, degreeIndex));
        }
        // Use the geographical calculator to create a polygon
        double lat = Double.parseDouble((String)payload.get("lat"));
        double lng = Double.parseDouble((String)payload.get("lng"));
        
        List<Double> offset1 = GeographicalCalculator.coordCalc(lat, lng, 33000.0, Double.parseDouble(myNums.get(runway))-5);
        List<Double> offset2 = GeographicalCalculator.coordCalc(lat, lng, 33000.0, Double.parseDouble(myNums.get(runway))+5);
        List<List<Double>> polygon = Arrays.asList(Arrays.asList(lat,lng),offset1,offset2);
        return new ResponseEntity(polygon,HttpStatus.OK);
    }
}
