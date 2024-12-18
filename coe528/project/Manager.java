/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;
import java.util.*;
import java.io.*;
/**
 *
 * @author SJona
 * 
 // Overview:
 // The Manager class is responsible for managing customer accounts, including adding, deleting, and loading customers.
    //Manager is mutable, since you can change the ArrayList of the object.
    //
    // The abstraction function is:
    //- Represents a manager with a username, password, role, and a list of customers.
    //AF(c) = A stack of distinct Customer called customers 
    //
    //Rep Invariant:
 // - Username, password, and role must not be null or empty.
 // - Customers list must not be null.
 **/
public class Manager {
    private final String username = "admin";
    private final String password = "admin";
    private final String role = "Manager";
    //the rep
    private ArrayList<Customer> customers;
    
    public Manager(){
        // EFFECTS: Creates a new Manager object
        this.customers = new ArrayList();
}
    public void deleteCustomer(String user)throws IOException{
        // REQUIRES: user cannot be null and must not exist as a text file
      // MODIFIES: user.txt
      //           customers  
      // EFFECTS: Removes file user.txt
      //          Removes a specfic object Customer from customers.
        for(int i = 0; i<this.customers.size();i++){
            if(this.customers.get(i).getUser().equals(user)){
                if(this.customers.get(i).getFile().delete()){
                    this.customers.remove(this.customers.get(i));
                return;
                }
            }
        }
            throw new IOException("");
                
            }
    public void addCustomer(String user, String pass) throws IOException{
        // REQUIRES: user cannot be null and must not exist as a text file
        //           pass cannot be null
        // MODIFIES: user.txt
        //           customers
        // EFFECTS: Returns the Customer
        // if the element user exist as a text file, throw IOException
        // (Note:In UserLogin.Java, catches the IOException)
        File file = new File(user+".txt");
        if(!file.exists()){
            if(file.createNewFile()){
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(user+ "\n" + pass + "\nCustomer\n100.00\nSilver");
                out.close();
                Customer c = new Customer(file);
                this.customers.add(c);
            }
        }else{
            throw new IOException("");
        }
    }
    
    public void loadCustomers()throws IOException{
         // MODIFIES: customers
        // EFFECTS: Adds Customers from text files to customers
        File file = new File(System.getProperty("user.dir"));
        FilenameFilter filter = new FilenameFilter(){
            @Override
            public boolean accept(File f, String name){
                return name.endsWith(".txt");
            }
        };
        File[] files = file.listFiles(filter);
        for(File i: files){
            this.customers.add(new Customer(i));
        }
    }
    
    public Customer getCustomer(String user)throws IOException{
        // REQUIRES: user can be anything.      
        // MODIFIES: this
        // EFFECTS: Returns the Customer
        // if the element is not in customers, returns null
        // (Note: In UserLogin.Java, catches the NullPointerException)
        for(int i=0;i<getAllCustomers().size();i++){
            Customer c = (Customer) getAllCustomers().get(i);
            BufferedReader in = new BufferedReader(new FileReader(c.getFile()));
            if(in.readLine().equals(user)){
                return this.customers.get(i);
            }
            in.close();
        }
        return null;
    }
    
    public String getUser(){
         // EFFECTS: Returns the String username
        return this.username;
    }
    
    public String getPass(){
        // EFFECTS: Returns the String password
        return this.password;
    }
    
    public String getRole(){
        // EFFECTS: Returns the String role
        return this.role;
    }
    
    public ArrayList getAllCustomers(){
        // EFFECTS: Returns the ArrayList of customers
        return this.customers;
    }
    
    public boolean repOk(){
         // EFFECTS: Returns true if the rep invariant holds for this
        // object; otherwise returns false
        //
        if(customers == null){
            return false;
        }
        for(int i=0;i<customers.size();i++){
            Object p = customers.get(i);
            if(!(p instanceof String)){
            return false;
        }
            for(int o = i+1; o<customers.size();o++){
                if(customers.get(i).equals(customers.get(0))){
                    return false;
                }
            }
        }
        return true;
    }
    
    public String toString(){
        
        // EFFECTS: Returns a string that contains the strings in the
        // stack and the top element. Implements the
        // abstraction function.
        String out = "AF(c) = ";
        for(int i = 0; i<customers.size();i++){
            out = out + customers.get(i) + " ";
        }
        return out;
    }
}
    
        
