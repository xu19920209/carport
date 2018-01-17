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
@Table(name = "hmy_collect", schema = "carport", catalog = "")
public class HmyCollect {
    private int hid;
    private Integer aid;
    private Integer bid;
    private Timestamp collectTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hid", nullable = false)
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
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
    @Column(name = "bid", nullable = true)
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "collectTime", nullable = true)
    public Timestamp getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Timestamp collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HmyCollect that = (HmyCollect) o;

        if (hid != that.hid) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (collectTime != null ? !collectTime.equals(that.collectTime) : that.collectTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (collectTime != null ? collectTime.hashCode() : 0);
        return result;
    }
}
