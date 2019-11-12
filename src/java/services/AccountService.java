/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    public boolean forgotPassword(String email, String path) throws Exception
    {
        //UserService us = new UserService();
        //User user = us.get(email);
        
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        if(user == null){
            return false;
        }
        String to = user.getEmail();
        String subject = "Forgot password - forgot";
        String template = path + "/emailtemplates/forgot.html";
        HashMap<String, String> tags = new HashMap<>();
        
        tags.put("firstname", user.getFname());
        tags.put("lastname", user.getLname());
        tags.put("email", user.getEmail());
        tags.put("password", user.getPassword());
        
        GmailService.sendMail(to, subject, template, tags);
        return true;
    }
    public User login(String email, String password, String path) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        
        if (user == null) {
            return null;
        }
        
        if (!user.getPassword().equals(password)) {
            return null;
        }
        
        Logger.getLogger(AccountService.class.getName())
                .log(Level.INFO, "User logged in: {0}", email);
        
        //GmailService.sendMail(user.getEmail(), "Logged In",
        //        "You have successfully logged into The Home Inventory Application.", false);
        
        String to = user.getEmail();
        String subject = "Home Inventory - login";
        String template = path + "/emailtemplates/login.html";
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFname());
        tags.put("date", ((new Date()).toString()));
        
        GmailService.sendMail(to, subject, template, tags);
        
        return user;
    }
    
}
