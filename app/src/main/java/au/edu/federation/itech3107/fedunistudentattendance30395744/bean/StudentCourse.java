package au.edu.federation.itech3107.fedunistudentattendance30395744.bean;

import org.litepal.crud.LitePalSupport;

public class StudentCourse extends LitePalSupport {
    private Integer id;
    private String title;
    private String sId;
    private String sName;
    private Integer sCheck;
    private String sClass;

    public StudentCourse(String title, String sId, String sName, Integer sCheck, String sClass) {
        this.title = title;
        this.sId = sId;
        this.sName = sName;
        this.sCheck = sCheck;
        this.sClass = sClass;
    }

    public StudentCourse() {
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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsCheck() {
        return sCheck;
    }

    public void setsCheck(Integer sCheck) {
        this.sCheck = sCheck;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }
}
