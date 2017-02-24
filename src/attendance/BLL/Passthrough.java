/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.User;
import attendance.DAL.UserManager;
import java.util.List;

/**
 *
 * @author Kristof
 */
/*public class Passthrough {
    private UserManager userman = new UserManager();
    
    public byte checkIfAdmin(String name) {
        if(userman.getUsers().contains(name) && name.equals("teacher")) {
            return 1;
        } else if(userman.getUsers().contains(name) && name.equals("student")){
            return 0;
        } else {
            return -1;
        }
    }
}*/

public class Passthrough
{
    private UserManager userManager = new UserManager();
    
    
    public List<String> getUsers()
    {
        return userManager.getUsers();
    }
    
}
