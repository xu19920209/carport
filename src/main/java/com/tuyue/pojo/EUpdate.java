package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@Entity
@Table(name = "e_update", schema = "carport", catalog = "")
public class EUpdate {
    private int eid;
    private Integer bid;
    private Integer aid;
    private Integer amendType;
    private String amendContent;
    private Timestamp amendTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid", nullable = false)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "bid", nullable = true)
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "aid", nullable = true)
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "amend_type", nullable = true)
    public Integer getAmendType() {
        return amendType;
    }

    public void setAmendType(Integer amendType) {
        this.amendType = amendType;
    }

    @Basic
    @Column(name = "amend_content", nullable = true, length = 255)
    public String getAmendContent() {
        return amendContent;
    }

    public void setAmendContent(String amendContent) {
        this.amendContent = amendContent;
    }

    @Basic
    @Column(name = "amend_time", nullable = true)
    public Timestamp getAmendTime() {
        return amendTime;
    }

    public void setAmendTime(Timestamp amendTime) {
        this.amendTime = amendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EUpdate eUpdate = (EUpdate) o;

        if (eid != eUpdate.eid) return false;
        if (bid != null ? !bid.equals(eUpdate.bid) : eUpdate.bid != null) return false;
        if (aid != null ? !aid.equals(eUpdate.aid) : eUpdate.aid != null) return false;
        if (amendType != null ? !amendType.equals(eUpdate.amendType) : eUpdate.amendType != null) return false;
        if (amendContent != null ? !amendContent.equals(eUpdate.amendContent) : eUpdate.amendContent != null)
            return false;
        if (amendTime != null ? !amendTime.equals(eUpdate.amendTime) : eUpdate.amendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (amendType != null ? amendType.hashCode() : 0);
        result = 31 * result + (amendContent != null ? amendContent.hashCode() : 0);
        result = 31 * result + (amendTime != null ? amendTime.hashCode() : 0);
        return result;
    }
}
