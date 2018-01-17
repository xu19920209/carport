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
@Table(name = "f_relation_me", schema = "carport", catalog = "")
public class FRelationMe {
    private int fid;
    private Integer bid;
    private Integer aid;
    private Timestamp relationTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid", nullable = false)
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
    @Column(name = "relation_time", nullable = true)
    public Timestamp getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Timestamp relationTime) {
        this.relationTime = relationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FRelationMe that = (FRelationMe) o;

        if (fid != that.fid) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (relationTime != null ? !relationTime.equals(that.relationTime) : that.relationTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (relationTime != null ? relationTime.hashCode() : 0);
        return result;
    }
}
