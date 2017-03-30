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
public class CurrentUser /*extends User*/ {
    
    //I DONT KNOOOOOOOOOOW
    /*private static CurrentUser self = null;
    private Teacher currentTeacher;
    private Student currentStudent;
    private boolean teacher;
    
    private CurrentUser() {
        
    }
    
    public CurrentUser getInstance() {
        if(self == null)
            self = new CurrentUser();
        return self;
    }

    public boolean isTeacher() {
        return teacher;
    }

    
    @Override
    public void setTeacher(int id, String name, String monogram, String email, String password) {
        currentStudent = null;
        currentTeacher = new Teacher(id, name, monogram, email, password);
        teacher = true;
    }

    @Override
    public void setStudent(int id, String name, String username, String password, String email, int classid) {
        currentTeacher = null;
        currentStudent = new Student(id, name, username, password, email, classid);
        teacher = false;
    }
    
    public T getCurUser() {
        return currentStudent;
    }
    
    public Teacher getCurTeacher() {
        return currentTeacher;
    }*/
}
