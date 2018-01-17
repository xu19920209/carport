package com.tuyue.web.publish.biz.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
public class BCarportOutlistBean {
    private int bid;
    private Integer aid;
    private Integer carportType;
    private String floor;
    private String carportNo;
    private Double maxSize;
    private String carSize;
    private Double rental;
    private String housesName;
    private String province;
    private String city;
    private String region;
    private String businessCircles;
    private String housesAdress;
    private String title;
    private String details;
    private String mainImg;
    private String linkman;
    private String pjone;
    private Integer isPrivary;
    private Integer isSale;
    private Double salePrice;
    private Double saleMoney;
    private Timestamp issueTime;
    private Integer enterpriseType;
    private Integer rentType;
    private Integer isPutaway;
    private Long pointNum;
    private Integer isLowerMoney;
    private Double lowerMoney;


    private Long picCount;//图片张数
    private Long collectSum;//收藏数
    private Long attentionSum;//关注数
    private Long shamSum;//虚假数


    @Override
    public String toString() {
        return "BCarportOutlistBean{" + "bid=" + bid + ", aid=" + aid + ", carportType=" + carportType + ", floor='" + floor + '\'' + ", carportNo='" + carportNo + '\'' + ", maxSize=" + maxSize + ", carSize='" + carSize + '\'' + ", rental=" + rental + ", housesName='" + housesName + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", region='" + region + '\'' + ", businessCircles='" + businessCircles + '\'' + ", housesAdress='" + housesAdress + '\'' + ", title='" + title + '\'' + ", details='" + details + '\'' + ", mainImg='" + mainImg + '\'' + ", linkman='" + linkman + '\'' + ", pjone='" + pjone + '\'' + ", isPrivary=" + isPrivary + ", isSale=" + isSale + ", salePrice=" + salePrice + ", saleMoney=" + saleMoney + ", issueTime=" + issueTime + ", enterpriseType=" + enterpriseType + ", rentType=" + rentType + ", isPutaway=" + isPutaway + ", pointNum=" + pointNum + ", isLowerMoney=" + isLowerMoney + ", lowerMoney=" + lowerMoney + ", picCount=" + picCount + ", collectSum=" + collectSum + ", attentionSum=" + attentionSum + ", shamSum=" + shamSum + '}';
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCarportType() {
        return carportType;
    }

    public void setCarportType(Integer carportType) {
        this.carportType = carportType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCarportNo() {
        return carportNo;
    }

    public void setCarportNo(String carportNo) {
        this.carportNo = carportNo;
    }

    public Double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Double maxSize) {
        this.maxSize = maxSize;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public Double getRental() {
        return rental;
    }

    public void setRental(Double rental) {
        BigDecimal bg = new BigDecimal(rental);
        double f = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.rental = f;
    }

    public String getHousesName() {
        return housesName;
    }

    public void setHousesName(String housesName) {
        this.housesName = housesName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBusinessCircles() {
        return businessCircles;
    }

    public void setBusinessCircles(String businessCircles) {
        this.businessCircles = businessCircles;
    }

    public String getHousesAdress() {
        return housesAdress;
    }

    public void setHousesAdress(String housesAdress) {
        this.housesAdress = housesAdress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPjone() {
        return pjone;
    }

    public void setPjone(String pjone) {
        this.pjone = pjone;
    }

    public Integer getIsPrivary() {
        return isPrivary;
    }

    public void setIsPrivary(Integer isPrivary) {
        this.isPrivary = isPrivary;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
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

    public Long getPicCount() {
        return picCount;
    }

    public void setPicCount(Long picCount) {
        this.picCount = picCount;
    }

    public Long getCollectSum() {
        return collectSum;
    }

    public void setCollectSum(Long collectSum) {
        this.collectSum = collectSum;
    }

    public Long getAttentionSum() {
        return attentionSum;
    }

    public void setAttentionSum(Long attentionSum) {
        this.attentionSum = attentionSum;
    }

    public Long getShamSum() {
        return shamSum;
    }

    public void setShamSum(Long shamSum) {
        this.shamSum = shamSum;
    }

}
