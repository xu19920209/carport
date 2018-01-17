package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/22.
 * @Modified By:
 */
@Entity
@Table(name = "i_feedback", schema = "carport", catalog = "")
public class IFeedback {
    private int iid;
    private Integer aid;
    private String content;
    private String tel;
    private Timestamp iTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iid")
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "aid")
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "i_time")
    public Timestamp getiTime() {
        return iTime;
    }

    public void setiTime(Timestamp iTime) {
        this.iTime = iTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IFeedback iFeedback = (IFeedback) o;

        if (iid != iFeedback.iid) return false;
        if (aid != null ? !aid.equals(iFeedback.aid) : iFeedback.aid != null) return false;
        if (content != null ? !content.equals(iFeedback.content) : iFeedback.content != null) return false;
        if (tel != null ? !tel.equals(iFeedback.tel) : iFeedback.tel != null) return false;
        if (iTime != null ? !iTime.equals(iFeedback.iTime) : iFeedback.iTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (iTime != null ? iTime.hashCode() : 0);
        return result;
    }
}
