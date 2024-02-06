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
                    return false;
                }
            }
            else
            {
                if(actual.get(i)/expected.get(i) < Threshold)
                {
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
        
        q1.setSpeed(1);
        q1.reset();
        
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
        MotionObjectMove c1 = new MotionObjectMove(0,0);
        c1.setMotionObject(a1);
        q1.register(c1);
        q1.setSpeed(ticks);
        q1.notifyObservers();
        assertEquals(true,this.WithinErrorThreshold(expected, a1.getPos(), t1));
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, value={2}")
    @MethodSource("dataProviderSpeedAircraft")
    public void TestSpeedCommand(double expected, Aircraft a1, double value, int direction )
    {
        CommandObjectAbstract c1 = CommandObjectAbstract.commandFactory("Speed", 1, value, direction).get(0);
        c1.setMotionObject(a1);
        MotionObjectSpeed c2 = new MotionObjectSpeed(0.0,0);
        c2.setMotionObject(a1);
        q1.register(c1);
        q1.register(c2);
        
        for (int i = 0; i < 100; i++)
        {
            q1.notifyObservers();
        }
        assertEquals(expected, a1.getSpeed());
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, value={2}, direction={3}")
    @MethodSource("dataProviderAngleAircraft")
    public void TestAngleCommand(double expected, Aircraft a1, double value, int direction )
    {
        MotionObjectTurn c1 = new MotionObjectTurn(value, direction);
        c1.setMotionObject(a1);
        q1.register(c1);
        q1.notifyObservers();
        assertEquals(expected, a1.getAngle().getValue());
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, value={2}, direction={3}")
    @MethodSource("dataProviderUnregisterAircraft")
    public void TestUnregister(double expected, Aircraft a1, double value, int direction )
    {
        MotionObjectTurn c1 = new MotionObjectTurn(value, direction);
        c1.setMotionObject(a1);
        q1.register(c1);
        for (int i = 0; i < 100; i++)
        {
            q1.notifyObservers();
        }
        assertEquals(expected, a1.getAngle().getValue());
    }
    
//    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, value={2}, direction={3}")
//    @MethodSource("dataProviderAccelerationAircraft")
//    public void TestAccelerationCommand(double expected, Aircraft a1, double value, int direction )
//    {
//        MotionObjectAcceleration c1 = new MotionObjectAcceleration(value, direction);
//        c1.setMotionObject(a1);
//        q1.register(c1);
//        q1.notifyObservers();
//        assertEquals(expected, a1.getAcceleration());
//    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, commandList={2}")
    @MethodSource("dataProviderAircraftPath")
    public void TestPathExecution(List<Double> expected, Aircraft a1,List<List<CommandObjectAbstract>> commands )
    {
        for (List<CommandObjectAbstract> l1 : commands)
        {
        for (CommandObjectAbstract o1 : l1)
        {
            o1.setMotionObject(a1);
            q1.register(o1);
        }
        }
        q1.notifyObservers();
        assertEquals(true, this.WithinErrorThreshold(expected, a1.getPos(), 0.9999));
    }
    
    @ParameterizedTest(name = "{index} => expected={0}, Aircraft={1}, action={2}")
    @MethodSource("dataProviderActionAngleAircraft")
    public void TestDecoderAngleCommand(double expected, Aircraft a1, String action)
    {
        CommandObjectAbstract c1 = CommandDecoder.decodeAction(action, a1);
        c1.setMotionObject(a1);
        q1.register(c1);
        for (int i = 0; i < 100; i++)
        {
            q1.notifyObservers();
        }
        assertEquals(expected, a1.getAngle().getValue());
    }
    
    
    
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("dataProviderBearingAircraft")
    public void TestBearingCalc(Double expected, List<List<Double>> given)
    {
        double bearing = Math.round(GeographicalCalculator.bearingCalc(given.get(0), given.get(1)) * 100.0) /100.0;
        assertEquals(expected,bearing);
    }
    
    private static Stream<Arguments> dataProviderActionAngleAircraft() {
        return Stream.of(
                Arguments.of(79.0,new Aircraft(17.5,67.9,100,new Angle(23.8)) , "turn left heading 079" ),
                Arguments.of(79.0,new Aircraft(17.5,67.9,100,new Angle(23.8)) , "turn right heading 079" ),
                Arguments.of(279.0,new Aircraft(17.5,67.9,100,new Angle(23.8)) , "turn left heading 279" )
        );
    }
    
    private static Stream<Arguments> dataProviderBearingAircraft() {
        return Stream.of(
                Arguments.of(96.51,Arrays.asList(Arrays.asList(39.099912,-94.581213),Arrays.asList(38.627089,-90.200203))),
                Arguments.of(240.0,Arrays.asList(Arrays.asList(45.89,-64.864),Arrays.asList(45.43474621,-65.97078946)))
        );
    }
    
    private static Stream<Arguments> dataProviderAircraftPath() {
        return Stream.of(
                Arguments.of(Arrays.asList(17.50700669,67.90590722),new Aircraft(17.5,67.9,100,new Angle(38.8)), Arrays.asList(CommandObjectAbstract.commandFactory("Move", 5, 0, 0),CommandObjectAbstract.commandFactory("Move", 5, 0, 0))),
                Arguments.of(Arrays.asList(17.56862933,67.94493091),new Aircraft(17.5,67.9,100,new Angle(38.8)), Arrays.asList(CommandObjectAbstract.commandFactory("Move", 30, 0, 0),CommandObjectAbstract.commandFactory("Turn", 1, 120, 1),CommandObjectAbstract.commandFactory("Move", 60, 0, 0))),
                Arguments.of(Arrays.asList(17.54634755,67.9389734),new Aircraft(17.5,67.9,100,new Angle(38.8)), Arrays.asList(CommandObjectAbstract.commandFactory("Move", 30, 0, 0),CommandObjectAbstract.commandFactory("Speed", 10, 10, 0),CommandObjectAbstract.commandFactory("Move", 30, 0, 0)))
        );
    }
    
    private static Stream<Arguments> dataProviderSpeedAircraft() {
        return Stream.of(
                Arguments.of(120,new Aircraft(17.5,67.9,100,new Angle(38.8),0.0) , 120, 0 ),
                Arguments.of(80,new Aircraft(17.5,67.9,100,new Angle(38.8),0.0) , 80, 1),
                Arguments.of(200,new Aircraft(17.5,67.9,150,new Angle(38.8),0.0) , 200, 0)
        );
    }
    
    private static Stream<Arguments> dataProviderAngleAircraft() {
        return Stream.of(
                Arguments.of(33.8,new Aircraft(17.5,67.9,100,new Angle(23.8)) , 10.5, 0 ),
                Arguments.of(303.2,new Aircraft(17.5,67.9,100,new Angle(313.2)) , 167.6, 1),
                Arguments.of(9.2,new Aircraft(17.5,67.9,150,new Angle(359.2)) , 120.7, 0)
        );
    }
    
    private static Stream<Arguments> dataProviderUnregisterAircraft() {
        return Stream.of(
                Arguments.of(34.3,new Aircraft(17.5,67.9,100,new Angle(23.8)) , 34.3, 0 ),
                Arguments.of(145.6,new Aircraft(17.5,67.9,100,new Angle(313.2)) , 145.6, 1),
                Arguments.of(119.9,new Aircraft(17.5,67.9,150,new Angle(359.2)) , 119.9, 0)
        );
    }
    
    private static Stream<Arguments> dataProviderAccelerationAircraft() {
        return Stream.of(
                Arguments.of(14.5,new Aircraft(17.5,67.9,100,new Angle(23.8),14.2) , 0.3, 0 ),
                Arguments.of(8.0,new Aircraft(17.5,67.9,100,new Angle(313.2),10.0) , 2.7, 1),
                Arguments.of(8.9,new Aircraft(17.5,67.9,150,new Angle(359.2),6.9) , 5, 0)
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
