/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BE;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Desmoswal
 */
public class Schedule
{
    int id;
    Date startTime;
    Date endTime;
    String classId;
    String teacher;
    String room;
    String subject;
    
    public String time;

    public Schedule(int id, Date startTime, Date endTime, String classId, String subject, String room, String teacher)
    {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classId = classId;
        this.teacher = teacher;
        this.room = room;
        this.subject = subject;
        
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

    public String getClassId()
    {
        return classId;
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

    
}
