/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BE;

/**
 *
 * @author Desmoswal
 */
public class User
{
    private boolean admin;
    
    public User(boolean admin)
    {
        this.admin = admin;
    }
    
    public boolean isAdmin()
    {
        return admin;
    }
}
