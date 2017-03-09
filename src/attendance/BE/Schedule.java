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
public class Schedule
{
    String time;
    String Class;
    String Teacher;
    String Room;

    public Schedule(String time, String Class, String Teacher, String Room)
    {
        this.time = time;
        this.Class = Class;
        this.Teacher = Teacher;
        this.Room = Room;
    }

    public String getTime()
    {
        return time;
    }

    public String getClassid()
    {
        return Class;
    }

    public String getTeacher()
    {
        return Teacher;
    }

    public String getRoom()
    {
        return Room;
    }
    
    
    
}
