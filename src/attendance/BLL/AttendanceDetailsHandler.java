/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.DAL.AttendanceDetailsManager;

/**
 *
 * @author Kristof
 */
public class AttendanceDetailsHandler {
    private AttendanceDetailsManager attMan = new AttendanceDetailsManager();
    
    public int calculateMissedTotal() {
        if(attMan.getAllCheckedin() != -10 || attMan.getAllSchedules() != -10) {
            return attMan.getAllSchedules() - attMan.getAllCheckedin();
        } else {
            return -1;
        }
    }
}
