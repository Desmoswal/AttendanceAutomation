/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class UserManager {
    private ArrayList<String> users = new ArrayList<>();
    
    public UserManager() {
        users.add("teacher");
        users.add("student");
    }
    
    public String getSpecificUser(int id) {
        return users.get(id);
    }
    public ArrayList<String> getUsers() {
        return users;
    }
}
