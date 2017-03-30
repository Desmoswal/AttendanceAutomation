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
public abstract class User {
    
    protected abstract void setTeacher(int id, String name, String monogram, String email, String password);
    protected abstract void setStudent(int id, String name, String username, String password, String email, int classid);
}
