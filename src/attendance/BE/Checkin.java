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
public class Checkin {
    private int studentId;
    private int schedId;

    public Checkin(int studentId, int schedId) {
        this.studentId = studentId;
        this.schedId = schedId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSchedId() {
        return schedId;
    }
    
    
}
