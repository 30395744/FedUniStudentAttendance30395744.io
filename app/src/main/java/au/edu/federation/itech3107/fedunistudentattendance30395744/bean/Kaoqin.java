package au.edu.federation.itech3107.fedunistudentattendance30395744.bean;

import org.litepal.crud.LitePalSupport;

public class Kaoqin extends LitePalSupport {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getKqtime() {
        return kqtime;
    }

    public void setKqtime(String kqtime) {
        this.kqtime = kqtime;
    }

    private Integer id;
    private String cname;
    private String sname;
    private String kqtime;



}
