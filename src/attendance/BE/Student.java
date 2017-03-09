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
public class Student {

    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private int classid;
    private boolean checkedIn;
    

    public String getName() {
        return name;
    }

    public Student(int id, String name, String username, String password, String email, int classid) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.classid = classid;
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
    
}
