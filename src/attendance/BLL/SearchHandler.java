/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.GUI.Controller.MainViewController;
import java.util.List;
import attendance.BE.Class;
import attendance.BE.Student;
import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class SearchHandler {
    public enum SearchType {STUDENT,CLASS};
    DataHandler datahandler = new DataHandler();
    
    public List<Class> searchClass(String word, List<Class> inWhat, SearchType type) {
        List<Class> filtered = new ArrayList<>();
        if(type == SearchType.CLASS) {
            //List<Class> classes = datahandler.getClasses();
            for (Class classs : inWhat) {
                if(classs.getName().toLowerCase().contains(word.toLowerCase())) {
                    filtered.add(classs);
                }
            }
            
        }
        return filtered;
    }
    
    public List<Student> searchStudent(String word, List<Student> inWhat, SearchType type) {
        List<Student> filtered = new ArrayList<>();
        
        if(type == SearchType.STUDENT) {
            //List<Student> students = datahandler.getStudents();
            for (Student student : inWhat) {
                if(student.getName().toLowerCase().contains(word.toLowerCase())) {
                    filtered.add(student);
                }
            }
        }
        return filtered;
    }
}
