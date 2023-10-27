package au.edu.federation.itech3107.fedunistudentattendance30395744.bean;

import org.litepal.crud.LitePalSupport;

public class Teacher extends LitePalSupport {
    private Integer id;
    private String teacherId;
    private String teacherName;
    private String teacherPassword;

    public Teacher(Integer id, String teacherId, String teacherName, String teacherPassword) {
        this.id = id;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }

    public Teacher(String teacherId, String teacherName, String teacherPassword) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }
}
