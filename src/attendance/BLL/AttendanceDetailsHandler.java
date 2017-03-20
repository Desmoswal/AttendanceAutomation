/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Schedule;
import attendance.DAL.AttendanceDetailsManager;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class AttendanceDetailsHandler {
    private AttendanceDetailsManager attMan = new AttendanceDetailsManager();
    
    /*public int calculateMissedTotal() {
        if(attMan.getAllCheckedin() != -10 || attMan.getAllSchedules() != -10) {
            return attMan.getAllSchedules() - attMan.getAllCheckedin();
        } else {
            return -1;
        }
    }*/
    
    public ArrayList<Schedule> getMissed() {
        return attMan.getMissedSchedules();
    }
    
    public String getTotalAttPercent() {
        System.out.println("checkedin: "+attMan.getAllCheckedin().size());
        System.out.println("all: "+attMan.getAllSchedules().size());
        System.out.println((attMan.getAllCheckedin().size() / attMan.getAllSchedules().size()));
        DecimalFormat numberFormat = new DecimalFormat("#.#");
        return numberFormat.format(((float)attMan.getAllCheckedin().size() / (float)attMan.getAllSchedules().size())*100);
    }
}
