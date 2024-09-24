/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingappgui.project;

/**
 *
 * @author Aadil
 */
public abstract class Level {
    protected int fee; 
    protected String level;
    
    public double getFee(){
        return fee; 
    } 
    
    public String getLevel(){
        return level; 
    }
    
    @Override
    public String toString(){
        return this.level; 
    }
}
