package com.tuyue.pojo;

import javax.persistence.*;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@Entity
@Table(name = "t_accesstoken", schema = "carport", catalog = "")
public class TAccesstoken {
    private int id;
    private Integer aid;
    private String accessToken;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAccesstoken that = (TAccesstoken) o;

        if (id != that.id) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        return result;
    }
}
