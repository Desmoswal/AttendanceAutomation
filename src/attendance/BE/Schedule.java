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
    
    public String time;

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
        
        this.time = startTime.getHours() + ":" + startTime.getMinutes() + " - " + endTime.getHours() + ":" + endTime.getMinutes();
       
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
}
