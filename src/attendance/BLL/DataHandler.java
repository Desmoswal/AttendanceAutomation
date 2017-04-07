/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.BE.Class;
import attendance.BE.Schedule;
import attendance.BE.Subject;
import attendance.DAL.DataManager;
import attendance.DAL.LoginManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Desmoswal
 */
public class DataHandler {

    private LoginManager loginManager = new LoginManager();
    private DataManager dataManager = new DataManager();
    private ScheduleHandler scheduleHandler = new ScheduleHandler();

    public List<Student> getStudents() {
        return loginManager.getStudents();
    }

    public List<Teacher> getTeachers() {
        return loginManager.getTeachers();
    }

    public List<Class> getClasses() {
        return dataManager.getClasses();
    }

    public List<Student> getStudentsByClass(int classId) {
        return dataManager.getStudentsByClass(classId);
    }

    public List<Subject> getSubjectsForStudent(int studentid) {
        return dataManager.getSubjectsForStudent(studentid);
    }

    public Student getStudent(int studentid) {
        return dataManager.getStudent(studentid);
    }

    public String calculateMostMissedDayOfWeekForStudent(int studentid, int classid) {

        String[] daysofweek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        ArrayList<Integer> missedWeekDays = new ArrayList<>();
        for (Schedule schedule : scheduleHandler.getMissedSchedulesForStudent(studentid, classid)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(schedule.getStartTime());
            int day = cal.get(Calendar.DAY_OF_WEEK);
            missedWeekDays.add(day);
        }
        int max = 0;
        int current = 0;
        int currentDay = 0;
        Set<Integer> unique = new HashSet<Integer>(missedWeekDays);
        for (Integer day : unique) {
            current = Collections.frequency(missedWeekDays, day);

            if (max < current) {
                max = current;
                currentDay = day;
            }
        }
        return daysofweek[currentDay - 1];
    }

    public String calculateAvgAttForClass(int classid) {
        List<Float> avgForStudents = new ArrayList<>();
        for (Student student : this.getStudentsByClass(classid)) {
            int attended = scheduleHandler.getAllCheckedinForStudent(student.getId(), student.getClassid()).size();
            int missed = scheduleHandler.getMissedSchedulesForStudent(student.getId(), student.getClassid()).size();
            int total = attended + missed;

            float avg = (float) attended / (float) total * 100;
            avgForStudents.add(avg);
        }
        float totalPercent = 0;
        for (Float avgForStudent : avgForStudents) {
            totalPercent += avgForStudent;
        }
        DecimalFormat numberFormat = new DecimalFormat("#.#");

        return numberFormat.format(totalPercent / avgForStudents.size());
    }

    public String calculateMostMissedCourseForClass(int classid) {
        List<String> missedSubjectNames = new ArrayList<>();
        for (Student student : this.getStudentsByClass(classid)) {
            for (Schedule schedule : scheduleHandler.getMissedSchedulesForStudent(student.getId(), student.getClassid())) {
                missedSubjectNames.add(schedule.getSubject());
            }
        }

        int max = 0;
        int current = 0;
        String currentSubject = null;
        Set<String> unique = new HashSet<String>(missedSubjectNames);
        for (String subject : unique) {
            current = Collections.frequency(missedSubjectNames, subject);

            if (max < current) {
                max = current;
                currentSubject = subject;
            }
        }
        return currentSubject;
    }

    public String calculateMostAttCourseForClass(int classid) {
        List<String> attSubjectNames = new ArrayList<>();
        for (Student student : this.getStudentsByClass(classid)) {
            for (Schedule schedule : scheduleHandler.getAllCheckedinForStudent(student.getId(), student.getClassid())) {
                attSubjectNames.add(schedule.getSubject());
            }
        }

        int max = 0;
        int current = 0;
        String currentSubject = null;
        Set<String> unique = new HashSet<String>(attSubjectNames);
        for (String subject : unique) {
            current = Collections.frequency(attSubjectNames, subject);

            if (max < current) {
                max = current;
                currentSubject = subject;
            }
        }
        return currentSubject;
    }

    public Student calculateMostMissedStudentForClass(int classid) {
        Map<Integer, Integer> missesForStudents = new HashMap<>();
        ArrayList<Integer> studentids = new ArrayList<>();
        for (Student student : this.getStudentsByClass(classid)) {
            int missed = scheduleHandler.getMissedSchedulesForStudent(student.getId(), student.getClassid()).size();
            missesForStudents.put(student.getId(), missed);
            studentids.add(student.getId());
        }

        int max = 0;
        int current = 0;
        int currentStudent = 0;
        for (Integer studentid : studentids) {
            if (missesForStudents.containsKey(studentid)) {
                int misses = missesForStudents.get(studentid);
                if (max < misses) {
                    max = misses;
                    currentStudent = studentid;
                }
            }
        }
        return this.getStudent(currentStudent);
    }

    public String calculateMostMissedStudentsMostMissedCourse(int classid) {
        int studentid = this.calculateMostMissedStudentForClass(classid).getId();
        List<String> missedSubjectNames = new ArrayList<>();
        ArrayList<Schedule> studentmisses = scheduleHandler.getMissedSchedulesForStudent(studentid, classid);
        for (Schedule schedule : studentmisses) {
            missedSubjectNames.add(schedule.getSubject());
        }

        int max = 0;
        int current = 0;
        String currentSubject = null;
        Set<String> unique = new HashSet<String>(missedSubjectNames);
        for (String subject : unique) {
            current = Collections.frequency(missedSubjectNames, subject);

            if (max < current) {
                max = current;
                currentSubject = subject;
            }
        }
        return currentSubject;
    }

    public Student calculateMostAttStudentForClass(int classid) {
        Map<Integer, Integer> attForStudents = new HashMap<>();
        ArrayList<Integer> studentids = new ArrayList<>();
        for (Student student : this.getStudentsByClass(classid)) {
            int attended = scheduleHandler.getAllCheckedinForStudent(student.getId(), student.getClassid()).size();
            attForStudents.put(student.getId(), attended);
            studentids.add(student.getId());
        }

        int max = 0;
        int current = 0;
        int currentStudent = 0;
        for (Integer studentid : studentids) {
            if (attForStudents.containsKey(studentid)) {
                int misses = attForStudents.get(studentid);
                if (max < misses) {
                    max = misses;
                    currentStudent = studentid;
                }
            }
        }
        return this.getStudent(currentStudent);
    }

    public String calculateMostAttStudentsMostAttCourse(int classid) {
        Student student = this.calculateMostAttStudentForClass(classid);

        if (student != null) {
            int studentid = student.getId();
            List<String> attSubjectNames = new ArrayList<>();
            ArrayList<Schedule> studentmisses = scheduleHandler.getAllCheckedinForStudent(studentid, classid);
            for (Schedule schedule : studentmisses) {
                attSubjectNames.add(schedule.getSubject());
            }

            int max = 0;
            int current = 0;
            String currentSubject = null;
            Set<String> unique = new HashSet<String>(attSubjectNames);
            for (String subject : unique) {
                current = Collections.frequency(attSubjectNames, subject);

                if (max < current) {
                    max = current;
                    currentSubject = subject;
                }
            }
            return currentSubject;
        } else {
            return "No attendance data.";
        }

    }
    
    public ArrayList<Subject> getAllSubjects()
    {
        return dataManager.getAllSubjects();
    }
}
