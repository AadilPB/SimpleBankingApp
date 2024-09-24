/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Aadil
 */
public class Manager extends AbstractUser {
    private static Manager instance = null;
    
    
    private Manager(String username, String password){
        this.username = username;
        this.password = password; 
        File managerInfo = new File(username + ".txt"); 
        if(!managerInfo.exists()){
                    try
                    {
                        managerInfo.createNewFile();
                    }
                    catch(IOException e){
                        System.out.println("An error has occurred"); 
                    }
                    try{
                    FileWriter info = new FileWriter(managerInfo);
                        try (BufferedWriter buffInfo = new BufferedWriter(info)) {
                            buffInfo.write(username + "\n" + password + "\n" + "manager");
                        }
                    }
                    catch(IOException e){
                        System.out.println("An error has occurred"); 
                    }
        }
            
                 
                
        
    }
    
    public static Manager getInstance(String username, String password){
        if(instance == null){
                instance = new Manager(username, password);
        }
        return instance; 
    }
    
 
    
    public boolean deleteCustomer(String username, ArrayList<Customer> customers){
        int deleteIndex = -1; 
        boolean hasDeleted = false;
        String directoryPath = "customersInformation/";
        File infoToDelete = new File(directoryPath + username + ".txt");
        if(infoToDelete.exists()){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getUsername().equals(username)){
                deleteIndex = i; 
                break;
            }
            
            }
            if(deleteIndex != -1){
                customers.remove(deleteIndex); 
                if(infoToDelete.delete()){
                    hasDeleted = true;
                    }
                }
            }
        return hasDeleted; 
    }
    
    public boolean addCustomer(String username, String password, ArrayList<Customer> customers){
        Customer newCustomer = new Customer(username, password, 100);
        boolean createdCustomer = true;
        String directoryPath = "customersInformation/"; 
        File customerInfo = new File(directoryPath + username + ".txt"); 
        if(customerInfo.exists()){
            System.out.println("File already exists");
            createdCustomer = false;
            return createdCustomer; 
        }else{
            
                    customers.add(newCustomer); 
                    try
                    {
                        customerInfo.createNewFile();
                    }
                    catch(IOException e){
                        System.out.println("An error has occurred"); 
                    }
                    try{
                    FileWriter info = new FileWriter(customerInfo);
                    BufferedWriter buffInfo = new BufferedWriter(info); 
                    buffInfo.write(username + "\n" + password + "\n" + "customer" + "\n" + "100"); 
                    buffInfo.close();
                    info.close(); 
                    }
                    catch(IOException e){
                        System.out.println("An error has occurred"); 
                    }
                }
    
        return createdCustomer; 
    }

    @Override
    public void login(String username, String password) {
              String fileName = username + ".txt";
        
        File file = new File(fileName);
        if(file.exists() && file.isFile()){
            try(Scanner reader = new Scanner(file)) { 
                
                String filedUsername = reader.nextLine().trim();
                String filedPassword = reader.nextLine().trim();
                String filedRole = reader.nextLine().trim();
                
            if (filedUsername.equals(username) && filedPassword.equals(password)) {
                this.loggedIn = true;
            }
                           
                
            } catch (FileNotFoundException ex) {
                System.out.println("The file could not be read.");
            }
            
            
            
        }
        
    }
    }
    
    
  

    
    

