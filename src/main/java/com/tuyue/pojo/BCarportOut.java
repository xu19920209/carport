package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/29.
 * @Modified By:
 */
@Entity
@Table(name = "b_carport_out", schema = "carport", catalog = "")
public class BCarportOut {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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
    @Column(name = "carport_type")
    public Integer getCarportType() {
        return carportType;
    }

    public void setCarportType(Integer carportType) {
        this.carportType = carportType;
    }

    @Basic
    @Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "carport_no")
    public String getCarportNo() {
        return carportNo;
    }

    public void setCarportNo(String carportNo) {
        this.carportNo = carportNo;
    }

    @Basic
    @Column(name = "max_size", columnDefinition="double(10,2) default '0.00'")
    public Double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Double maxSize) {
        this.maxSize = maxSize;
    }

    @Basic
    @Column(name = "car_size")
    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    @Basic
    @Column(name = "rental",columnDefinition="double(10,2) default '0.00'")
    public Double getRental() {
        return rental;
    }

    public void setRental(Double rental) {
        this.rental = rental;
    }

    @Basic
    @Column(name = "houses_name")
    public String getHousesName() {
        return housesName;
    }

    public void setHousesName(String housesName) {
        this.housesName = housesName;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "business_circles")
    public String getBusinessCircles() {
        return businessCircles;
    }

    public void setBusinessCircles(String businessCircles) {
        this.businessCircles = businessCircles;
    }

    @Basic
    @Column(name = "houses_adress")
    public String getHousesAdress() {
        return housesAdress;
    }

    public void setHousesAdress(String housesAdress) {
        this.housesAdress = housesAdress;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "main_img")
    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    @Basic
    @Column(name = "linkman")
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    @Basic
    @Column(name = "pjone")
    public String getPjone() {
        return pjone;
    }

    public void setPjone(String pjone) {
        this.pjone = pjone;
    }

    @Basic
    @Column(name = "is_privary")
    public Integer getIsPrivary() {
        return isPrivary;
    }

    public void setIsPrivary(Integer isPrivary) {
        this.isPrivary = isPrivary;
    }

    @Basic
    @Column(name = "is_sale")
    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    @Basic
    @Column(name = "sale_price", columnDefinition="double(10,2) default '0.00'")
    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    @Basic
    @Column(name = "sale_money", columnDefinition="double(10,2) default '0.00'")
    public Double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }

    @Basic
    @Column(name = "issue_time")
    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }

    @Basic
    @Column(name = "enterprise_type")
    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    @Basic
    @Column(name = "rent_type")
    public Integer getRentType() {
        return rentType;
    }

    public void setRentType(Integer rentType) {
        this.rentType = rentType;
    }

    @Basic
    @Column(name = "is_putaway")
    public Integer getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
    }

    @Basic
    @Column(name = "point_num")
    public Long getPointNum() {
        return pointNum;
    }

    public void setPointNum(Long pointNum) {
        this.pointNum = pointNum;
    }

    @Basic
    @Column(name = "is_lower_money")
    public Integer getIsLowerMoney() {
        return isLowerMoney;
    }

    public void setIsLowerMoney(Integer isLowerMoney) {
        this.isLowerMoney = isLowerMoney;
    }

    @Basic
    @Column(name = "lower_money", columnDefinition="double(10,2) default '0.00'")
    public Double getLowerMoney() {
        return lowerMoney;
    }

    public void setLowerMoney(Double lowerMoney) {
        this.lowerMoney = lowerMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BCarportOut that = (BCarportOut) o;

        if (bid != that.bid) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (carportType != null ? !carportType.equals(that.carportType) : that.carportType != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (carportNo != null ? !carportNo.equals(that.carportNo) : that.carportNo != null) return false;
        if (maxSize != null ? !maxSize.equals(that.maxSize) : that.maxSize != null) return false;
        if (carSize != null ? !carSize.equals(that.carSize) : that.carSize != null) return false;
        if (rental != null ? !rental.equals(that.rental) : that.rental != null) return false;
        if (housesName != null ? !housesName.equals(that.housesName) : that.housesName != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (businessCircles != null ? !businessCircles.equals(that.businessCircles) : that.businessCircles != null)
            return false;
        if (housesAdress != null ? !housesAdress.equals(that.housesAdress) : that.housesAdress != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (mainImg != null ? !mainImg.equals(that.mainImg) : that.mainImg != null) return false;
        if (linkman != null ? !linkman.equals(that.linkman) : that.linkman != null) return false;
        if (pjone != null ? !pjone.equals(that.pjone) : that.pjone != null) return false;
        if (isPrivary != null ? !isPrivary.equals(that.isPrivary) : that.isPrivary != null) return false;
        if (isSale != null ? !isSale.equals(that.isSale) : that.isSale != null) return false;
        if (salePrice != null ? !salePrice.equals(that.salePrice) : that.salePrice != null) return false;
        if (saleMoney != null ? !saleMoney.equals(that.saleMoney) : that.saleMoney != null) return false;
        if (issueTime != null ? !issueTime.equals(that.issueTime) : that.issueTime != null) return false;
        if (enterpriseType != null ? !enterpriseType.equals(that.enterpriseType) : that.enterpriseType != null)
            return false;
        if (rentType != null ? !rentType.equals(that.rentType) : that.rentType != null) return false;
        if (isPutaway != null ? !isPutaway.equals(that.isPutaway) : that.isPutaway != null) return false;
        if (pointNum != null ? !pointNum.equals(that.pointNum) : that.pointNum != null) return false;
        if (isLowerMoney != null ? !isLowerMoney.equals(that.isLowerMoney) : that.isLowerMoney != null) return false;
        if (lowerMoney != null ? !lowerMoney.equals(that.lowerMoney) : that.lowerMoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (carportType != null ? carportType.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (carportNo != null ? carportNo.hashCode() : 0);
        result = 31 * result + (maxSize != null ? maxSize.hashCode() : 0);
        result = 31 * result + (carSize != null ? carSize.hashCode() : 0);
        result = 31 * result + (rental != null ? rental.hashCode() : 0);
        result = 31 * result + (housesName != null ? housesName.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (businessCircles != null ? businessCircles.hashCode() : 0);
        result = 31 * result + (housesAdress != null ? housesAdress.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (mainImg != null ? mainImg.hashCode() : 0);
        result = 31 * result + (linkman != null ? linkman.hashCode() : 0);
        result = 31 * result + (pjone != null ? pjone.hashCode() : 0);
        result = 31 * result + (isPrivary != null ? isPrivary.hashCode() : 0);
        result = 31 * result + (isSale != null ? isSale.hashCode() : 0);
        result = 31 * result + (salePrice != null ? salePrice.hashCode() : 0);
        result = 31 * result + (saleMoney != null ? saleMoney.hashCode() : 0);
        result = 31 * result + (issueTime != null ? issueTime.hashCode() : 0);
        result = 31 * result + (enterpriseType != null ? enterpriseType.hashCode() : 0);
        result = 31 * result + (rentType != null ? rentType.hashCode() : 0);
        result = 31 * result + (isPutaway != null ? isPutaway.hashCode() : 0);
        result = 31 * result + (pointNum != null ? pointNum.hashCode() : 0);
        result = 31 * result + (isLowerMoney != null ? isLowerMoney.hashCode() : 0);
        result = 31 * result + (lowerMoney != null ? lowerMoney.hashCode() : 0);
        return result;
    }
}
