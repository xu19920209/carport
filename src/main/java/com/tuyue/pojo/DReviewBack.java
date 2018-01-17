package com.tuyue.pojo;

import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@Entity
@Table(name = "d_review_back", schema = "carport", catalog = "")
public class DReviewBack {
    private int did;
    private Integer aid;
    private Integer bid;
    private Integer reviewType;
    private Integer trueGrade;
    private Integer serveGrade;
    private String reviewContent;
    private Timestamp reviewTime;
    private Integer isApprove;
    private String userName;
    private String userImg;
    private Integer backType;
    private Integer backId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did", nullable = false)
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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
    @Column(name = "review_type", nullable = true)
    public Integer getReviewType() {
        return reviewType;
    }

    public void setReviewType(Integer reviewType) {
        this.reviewType = reviewType;
    }

    @Basic
    @Column(name = "true_grade", nullable = true)
    public Integer getTrueGrade() {
        return trueGrade;
    }

    public void setTrueGrade(Integer trueGrade) {
        this.trueGrade = trueGrade;
    }

    @Basic
    @Column(name = "serve_grade", nullable = true)
    public Integer getServeGrade() {
        return serveGrade;
    }

    public void setServeGrade(Integer serveGrade) {
        this.serveGrade = serveGrade;
    }

    @Basic
    @Column(name = "review_content", nullable = true, length = 255)
    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    @Basic
    @Column(name = "review_time", nullable = true)
    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Basic
    @Column(name = "is_approve", nullable = true)
    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_img", nullable = true, length = 50)
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Basic
    @Column(name = "back_type", nullable = true)
    public Integer getBackType() {
        return backType;
    }

    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    @Basic
    @Column(name = "back_id", nullable = true)
    public Integer getBackId() {
        return backId;
    }

    public void setBackId(Integer backId) {
        this.backId = backId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DReviewBack that = (DReviewBack) o;

        if (did != that.did) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (reviewType != null ? !reviewType.equals(that.reviewType) : that.reviewType != null) return false;
        if (trueGrade != null ? !trueGrade.equals(that.trueGrade) : that.trueGrade != null) return false;
        if (serveGrade != null ? !serveGrade.equals(that.serveGrade) : that.serveGrade != null) return false;
        if (reviewContent != null ? !reviewContent.equals(that.reviewContent) : that.reviewContent != null)
            return false;
        if (reviewTime != null ? !reviewTime.equals(that.reviewTime) : that.reviewTime != null) return false;
        if (isApprove != null ? !isApprove.equals(that.isApprove) : that.isApprove != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userImg != null ? !userImg.equals(that.userImg) : that.userImg != null) return false;
        if (backType != null ? !backType.equals(that.backType) : that.backType != null) return false;
        if (backId != null ? !backId.equals(that.backId) : that.backId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (reviewType != null ? reviewType.hashCode() : 0);
        result = 31 * result + (trueGrade != null ? trueGrade.hashCode() : 0);
        result = 31 * result + (serveGrade != null ? serveGrade.hashCode() : 0);
        result = 31 * result + (reviewContent != null ? reviewContent.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        result = 31 * result + (isApprove != null ? isApprove.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userImg != null ? userImg.hashCode() : 0);
        result = 31 * result + (backType != null ? backType.hashCode() : 0);
        result = 31 * result + (backId != null ? backId.hashCode() : 0);
        return result;
    }
}
