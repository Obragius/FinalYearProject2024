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
    
    public AircraftTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("dataProviderAircraft")
    public void TestNewCoord(List<Double> expected, List<Double> given)
    {
        Aircraft a1 = new Aircraft();
        Angle angle = new Angle();
        angle.setValue(given.get(0));
        a1.setAngle(angle);
        a1.setSpeed(given.get(1));
        a1.setxPos(given.get(2));
        a1.setyPos(given.get(3));
        assertEquals(expected,GeographicalCalculator.coordCalc(a1.getxPos(), a1.getyPos(), a1.getSpeed(), a1.getAngle().getValue()));
    }

    private static Stream<Arguments> dataProviderAircraft() {
        return Stream.of(
                Arguments.of(Arrays.asList(10.00088541,10.00015853), Arrays.asList(10.0,100.0,10.0,10.0)),
                Arguments.of(Arrays.asList(19.99999998,9.99712970), Arrays.asList(270.0,300.0,20.0,10.0)),
                Arguments.of(Arrays.asList(50.00161615,48.00122634), Arrays.asList(26.0,200.0,50.0,48.0))
        );
    }
}
