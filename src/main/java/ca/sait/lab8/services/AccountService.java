package ca.sait.lab8.services;

import ca.sait.lab8.dataaccess.UserDB;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ca.sait.lab8.models.*;


public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    public boolean forgotPassword(String email, String path) {
        UserDB userDB = new UserDB();
        
        try {
            
            User user = userDB.get(email);
                String to = user.getEmail();
                String subject = "Notes App password";
                String template = path + "/emailtemplates/findPassword.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                tags.put("email", user.getEmail());
                tags.put("password", user.getPassword());
                
                
                GmailService.sendMail(to, subject, template, tags);
               
                
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }    
}   

