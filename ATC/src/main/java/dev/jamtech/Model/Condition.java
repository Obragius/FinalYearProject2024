/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Condition 
{

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    private String attribute;
    private double value;

    public double getSign() {
        return sign;
    }

    public void setSign(double sign) {
        this.sign = sign;
    }
    private double sign;
    
}
