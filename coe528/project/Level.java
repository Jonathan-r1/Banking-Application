/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.*;
/**
 *
 * @author SJona
 */
public abstract class Level {
    
    public Level(){
    }
    //gets fee associated with the level
    public abstract double getFee();
    //sets the level for the customer given the amount of money
    public abstract void currentLevel (Customer c)throws IOException;
    //displays customer's level as a String
    public abstract String toString();
}
    
