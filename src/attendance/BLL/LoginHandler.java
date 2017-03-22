/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.CurrentStudent;
import attendance.BE.CurrentTeacher;
import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.DAL.LoginManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class LoginHandler {
    
    private DataHandler dm = new DataHandler();
    private LoginManager loginManager = new LoginManager();
    CurrentStudent currentStudent = CurrentStudent.getInstance();
    CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    
    /**
     * checks login credetials and tells if login is approved or not, and which type of login is it.
     * 
     *  ==STATUS CODES:==
     *      0: login ok, type: student
     *      10: login ok, type: teacher
     *      1: invalid username
     *      2: invalid password
     *      -1: empty username
     *      -2: empty password
     *      -3 unknown error (no if() reached (that's impossible but netbeans rulez...)
     * @param username
     * @param password
     * @return "correct status code"
     */
    public int checkLogin(String username, String password) {
        
        int status = -3;
        
        if(!username.isEmpty() && username != null) { //username empty check
            if(!password.isEmpty() && password != null) { //password empty check
                for (Student student : dm.getStudents()) { //check for each student first
                    if(student.getUsername().equals(username)) { //check current student username matches
                        if(student.getPassword().equals(password)) { //check current student password matches
                            currentStudent.setId(student.getId());
                            currentStudent.setName(student.getName());
                            currentStudent.setUsername(student.getUsername());
                            currentStudent.setPassword(student.getPassword());
                            currentStudent.setEmail(student.getEmail());
                            currentStudent.setClassid(student.getClassid());
                            loginManager.setOnline(currentStudent, 1);
                            System.out.println("Current Student Name: " + currentStudent.getName());
                            System.out.println(currentStudent.getName() + " is now online.");
                            return 0; //return ok
                        } else {
                            return 2; //return invalid pass
                        }
                    } else {
                        status = 1; //return invalid username
                    }
                }
                
                for (Teacher teacher : dm.getTeachers()) { //check for each teacher after students
                    if(teacher.getMonogram().toLowerCase().equals(username) || teacher.getMonogram().equals(username)) { //check current teacher username (monogram)
                        if(teacher.getPassword().equals(password)) { //check current teacher password
                            currentTeacher.setId(teacher.getId());
                            currentTeacher.setName(teacher.getName());
                            currentTeacher.setMonogram(teacher.getMonogram());
                            currentTeacher.setEmail(teacher.getEmail());
                            currentTeacher.setPassword(teacher.getPassword());
                            System.out.println("Current Teacher: " + currentTeacher.getName());
                            return 10; //return ok
                        } else {
                            return 2; //return invalid pass
                        }
                    } else {
                        status = 1; //return invalid username
                    }
                }
            } else {
                return -2; //return empty pass
            }
        } else {
            return -1; //return empty username
        }
        
        return status; //return unknown error
    }
    
    public void setOnline(CurrentStudent student, int state)
    {
        loginManager.setOnline(student, state);
    }
    
    public ArrayList<Student> getOnlineStudents()
    {
        return loginManager.getOnlineStudents();
    }
}
