/**
 * User.java
 * 
 * 
 * @author Aadil Bholat
 * 
 */
package bankingappgui.project;


public interface User {
    //OVERVIEW: User interface defines the shared methods of all users
    
    //Modifies: the users login status
    //Requires username String and passwords String inputs
    void login(String username, String password);
    
    //Modifies: the users login status
    void logout(); 
    
}
