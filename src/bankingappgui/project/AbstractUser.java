/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Aadil
 */
abstract class AbstractUser implements User {
    
    protected String username; 
    protected String password; 
    protected String role; 
    protected boolean loggedIn = false;
    
    
    @Override
    public abstract void login(String username, String password); 
  
    @Override
    public void logout(){
        this.loggedIn = false;
    } 
    
    public String getUsername(){return this.username;}

    public String getPassword(){return this.password;}
    
    public String getRole(){return this.role;} 
    
    public boolean getLoginStatus(){return this.loggedIn;}
    
}
