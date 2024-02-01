/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dev.jamtech.ATC;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class AircraftTest {
    
    Queue q1 = Queue.getInstance();
    
    public boolean WithinErrorThreshold(List<Double> expected, List<Double> actual, double Threshold)
    {
        for (int i = 0; i < expected.size(); i++)
        {
            if (actual.get(i) >= expected.get(i) )
            {
                if(expected.get(i)/actual.get(i) < Threshold)
                {
                    System.out.println(expected.get(i)/actual.get(i));
                    return false;
                }
            }
            else
            {
                if(actual.get(i)/expected.get(i) < Threshold)
                {
                    System.out.println(actual.get(i)/expected.get(i));
                    return false;
                }
            }
        }
        return true;
    }
    
    public AircraftTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("dataProviderAircraft")
    public void TestNewCoord(List<Double> expected, List<Double> given)
    {
        // Set threshold 
        double t1 = 0.99999;
        Aircraft a1 = new Aircraft();
        Angle angle = new Angle();
        angle.setValue(given.get(0));
        a1.setAngle(angle);
        a1.setSpeed(given.get(1));
        a1.setxPos(given.get(2));
        a1.setyPos(given.get(3));
        List<Double> result = GeographicalCalculator.coordCalc(a1.getxPos(), a1.getyPos(), a1.getSpeed(), a1.getAngle().getValue());
        assertEquals(true,this.WithinErrorThreshold(expected, result, t1));
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, ticks={2}")
    @MethodSource("dataProviderMoveAircraft")
    public void TestMoveCommand(List<Double> expected, Aircraft a1, int ticks)
    {
        // Set threshold 
        double t1 = 0.99999;
        MotionObjectMove c1 = new MotionObjectMove();
        c1.setMotionObject(a1);
        q1.register(c1);
        q1.setSpeed(ticks);
        q1.notifyObservers();
        assertEquals(true,this.WithinErrorThreshold(expected, a1.getPos(), t1));
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, value={2}, direction={3}")
    @MethodSource("dataProviderSpeedAircraft")
    public void TestSpeedCommand(double expected, Aircraft a1, double value, int direction )
    {
        // Set threshold 
        double t1 = 0.99999;
        MotionObjectSpeed c1 = new MotionObjectSpeed(value, direction);
        c1.setMotionObject(a1);
        q1.register(c1);
        q1.notifyObservers();
        assertEquals(expected, a1.getSpeed());
    }
    
    private static Stream<Arguments> dataProviderSpeedAircraft() {
        return Stream.of(
                Arguments.of(120,new Aircraft(17.5,67.9,100,new Angle(38.8)) , 20, 0 ),
                Arguments.of(80,new Aircraft(17.5,67.9,100,new Angle(38.8)) , 20, 1),
                Arguments.of(200,new Aircraft(17.5,67.9,150,new Angle(38.8)) , 50, 0)
        );
    }
    
    private static Stream<Arguments> dataProviderMoveAircraft() {
        return Stream.of(
                Arguments.of(Arrays.asList(17.5350317,67.92954064),new Aircraft(17.5,67.9,100,new Angle(38.8)) , 50),
                Arguments.of(Arrays.asList(6.91127474,78.38536438),new Aircraft(7.0,78.4,200,new Angle(189.3)) , 50),
                Arguments.of(Arrays.asList(76.27145825,47.44359562),new Aircraft(76.29,47.23,300,new Angle(110.0)) , 20)
        );
    }
    

    private static Stream<Arguments> dataProviderAircraft() {
        return Stream.of(
                Arguments.of(Arrays.asList(10.00088541,10.00015853), Arrays.asList(10.0,100.0,10.0,10.0)),
                Arguments.of(Arrays.asList(19.99999998,9.99712970), Arrays.asList(270.0,300.0,20.0,10.0)),
                Arguments.of(Arrays.asList(50.00161615,48.00122634), Arrays.asList(26.0,200.0,50.0,48.0))
        );
    }
}
