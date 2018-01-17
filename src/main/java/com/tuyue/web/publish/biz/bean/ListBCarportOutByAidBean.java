package com.tuyue.web.publish.biz.bean;

import java.sql.Timestamp;

/**
 * Created by Yui on 2017/9/23.
 */
public class ListBCarportOutByAidBean {
    private int bid;
    private String title;//标题
    private Integer rentType;//类目 出租转让（1出租2转让）
    private Double rental;//价格
    private Integer isPutaway;//是否上架（1是2否）
    private Long pointNum;//点击量
    private Timestamp issueTime;//发布时间
    private Integer isLowerMoney;//是否降价
    private Double lowerMoney;//降价价格

    @Override
    public String toString() {
        return "ListBCarportOutByAidBean{" +
                "bid=" + bid +
                ", title='" + title + '\'' +
                ", rentType=" + rentType +
                ", rental=" + rental +
                ", isPutaway=" + isPutaway +
                ", pointNum=" + pointNum +
                ", issueTime=" + issueTime +
                ", isLowerMoney=" + isLowerMoney +
                ", lowerMoney=" + lowerMoney +
                '}';
    }

    public ListBCarportOutByAidBean() {
    }

    public ListBCarportOutByAidBean(int bid, String title, Integer rentType, Double rental, Integer isPutaway, Long pointNum, Timestamp issueTime, Integer isLowerMoney, Double lowerMoney) {

        this.bid = bid;
        this.title = title;
        this.rentType = rentType;
        this.rental = rental;
        this.isPutaway = isPutaway;
        this.pointNum = pointNum;
        this.issueTime = issueTime;
        this.isLowerMoney = isLowerMoney;
        this.lowerMoney = lowerMoney;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    public Double getRental() {
        return rental;
    }

    public void setRental(Double rental) {
        this.rental = rental;
    }

    public Integer getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
    }

    public Long getPointNum() {
        return pointNum;
    }

    public void setPointNum(Long pointNum) {
        this.pointNum = pointNum;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }

    public Integer getIsLowerMoney() {
        return isLowerMoney;
    }

    public void setIsLowerMoney(Integer isLowerMoney) {
        this.isLowerMoney = isLowerMoney;
    }

    public Double getLowerMoney() {
        return lowerMoney;
    }

    public void setLowerMoney(Double lowerMoney) {
        this.lowerMoney = lowerMoney;
    }
}
