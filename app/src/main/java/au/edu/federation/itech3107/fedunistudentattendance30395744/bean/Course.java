package au.edu.federation.itech3107.fedunistudentattendance30395744.bean;

import org.litepal.crud.LitePalSupport;

public class Course extends LitePalSupport {
    private Integer id;
    private String title;
    private String teacher;
    private String startDate;
    private String endDate;

    public Course(String title, String teacher, String startDate, String endDate) {
        this.title = title;
        this.teacher = teacher;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
