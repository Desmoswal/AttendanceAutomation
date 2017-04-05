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
    
    public <T> List<T> search(String word, List<T> inWhat, SearchType type) {
        List<T> filtered = new ArrayList<>();
        
        if(type == SearchType.CLASS) {
            for (T t : inWhat) {
                if(t instanceof Class) {
                    if(((Class) t).getName().toLowerCase().contains(word.toLowerCase())) {
                        filtered.add(t);
                    }
                }
            }
        } else
        if(type == SearchType.STUDENT) {
            for (T t : inWhat) {
                if(t instanceof Student) {
                    if(((Student) t).getName().toLowerCase().contains(word.toLowerCase())) {
                        filtered.add(t);
                    }
                }
            }
        }
        
        return filtered;
    }
}
