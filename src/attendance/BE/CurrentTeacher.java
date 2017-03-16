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
public class CurrentTeacher
{
    int id;
    String name;
    String monogram;
    String email;
    String password;
    
    private static CurrentTeacher self;
    
    private CurrentTeacher()
    {

    }

    public static CurrentTeacher getInstance()
    {
        if(self == null)
        {
            self = new CurrentTeacher();
        }
        
        return self;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getMonogram()
    {
        return monogram;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMonogram(String monogram)
    {
        this.monogram = monogram;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    
}
