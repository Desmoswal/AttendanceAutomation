/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BE;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Desmoswal
 */
public class Schedule
{
    int id;
    int classId;
    Date startTime;
    Date endTime;
    String className;
    String teacher;
    String room;
    String subject;
    boolean canceled = false;
    
    public String time;
    String date;

    public Schedule(int id, Date startTime, Date endTime, int classId, String className, String subject, String room, String teacher)
    {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
        this.teacher = teacher;
        this.room = room;
        this.subject = subject;
        this.classId = classId;
        
        String startHours = "" + startTime.getHours();
        String startMinutes = "" + startTime.getMinutes();
        String endHours = "" +endTime.getHours();
        String endMinutes = "" +endTime.getMinutes();
        if(startHours.length() == 1) {
            startHours = "0" + startHours;
        }
        if(startMinutes.length() == 1) {
            startMinutes = "0" + startMinutes;
        }
        if(endHours.length() == 1) {
            endHours = "0" +endHours;
        }
        if(endMinutes.length() == 1) {
            endMinutes = "0" + endMinutes;
        }
        this.time = startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes;
        int mon = startTime.getMonth()+1;
        int year = startTime.getYear()+1900;
        this.date = startTime.getDate()+"-"+mon+"-"+year;
       
    }

    public int getId()
    {
        return id;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public String getClassName()
    {
        return className;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public String getRoom()
    {
        return room;
    }

    public String getTime()
    {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public int getClassId() {
        return classId;
    }

    public String getDate() {
        return date;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }
    
}
