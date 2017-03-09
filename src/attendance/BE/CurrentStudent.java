/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BE;

/**
 *
 * @author Kristof
 */
public class CurrentStudent {

    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private int classid;
    private boolean checkedIn;
    
    private static CurrentStudent self;
    
    
    private CurrentStudent()
    {

    }

    public static CurrentStudent getInstance()
    {
        if(self == null)
        {
            self = new CurrentStudent();
        }
        
        return self;
    }
    
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getClassid() {
        return classid;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setClassid(int classid)
    {
        this.classid = classid;
    }

    public void setCheckedIn(boolean checkedIn)
    {
        this.checkedIn = checkedIn;
    }
    
    
}
