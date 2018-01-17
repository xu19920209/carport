package com.tuyue.pojo;

import javax.persistence.*;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@Entity
@Table(name = "c_carport_img", schema = "carport", catalog = "")
public class CCarportImg {
    private int cid;
    private Integer bid;
    private String imgUrl;
    private Integer imgType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
    @Column(name = "img_url", nullable = true, length = 100)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "img_type", nullable = true)
    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCarportImg that = (CCarportImg) o;

        if (cid != that.cid) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;
        if (imgType != null ? !imgType.equals(that.imgType) : that.imgType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (imgType != null ? imgType.hashCode() : 0);
        return result;
    }
}
