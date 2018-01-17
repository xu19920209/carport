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
@Table(name = "g_look_me", schema = "carport", catalog = "")
public class GLookMe {
    private int gid;
    private Integer mainId;
    private Integer lookId;
    private Timestamp lookTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "main_id", nullable = true)
    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    @Basic
    @Column(name = "look_id", nullable = true)
    public Integer getLookId() {
        return lookId;
    }

    public void setLookId(Integer lookId) {
        this.lookId = lookId;
    }

    @Basic
    @Column(name = "look_time", nullable = true)
    public Timestamp getLookTime() {
        return lookTime;
    }

    public void setLookTime(Timestamp lookTime) {
        this.lookTime = lookTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GLookMe gLookMe = (GLookMe) o;

        if (gid != gLookMe.gid) return false;
        if (mainId != null ? !mainId.equals(gLookMe.mainId) : gLookMe.mainId != null) return false;
        if (lookId != null ? !lookId.equals(gLookMe.lookId) : gLookMe.lookId != null) return false;
        if (lookTime != null ? !lookTime.equals(gLookMe.lookTime) : gLookMe.lookTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (mainId != null ? mainId.hashCode() : 0);
        result = 31 * result + (lookId != null ? lookId.hashCode() : 0);
        result = 31 * result + (lookTime != null ? lookTime.hashCode() : 0);
        return result;
    }
}
