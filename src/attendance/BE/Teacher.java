/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BE;

import java.util.ArrayList;

/**
 *
 * @author Desmoswal
 */
public class Teacher
{
    private int id;
    private String name;
    private String monogram;
    private String email;
    private String password;
    private ArrayList<Subject> subjects;

    public Teacher(int id, String name, String monogram, String email, String password) {
        this.id = id;
        this.name = name;
        this.monogram = monogram;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMonogram() {
        return monogram;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
