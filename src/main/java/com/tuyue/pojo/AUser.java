package com.tuyue.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@Entity
@Table(name = "a_user", schema = "carport", catalog = "")
public class AUser {
    private int aid;
    private String userName;
    private String name;
    private String tel;
    private String password;
    private String flag;
    private String ioc;
    private String sex;
    private String province;
    private String city;
    private String region;
    private Date birthday;
    private String address;
    private String email;
    private String phone;
    private String qq;
    private String realName;
    private Integer papersType;
    private String identityNo;
    private Integer pproveType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid", nullable = false)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "flag", nullable = true, length = 2)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "ioc", nullable = true, length = 100)
    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "province", nullable = true, length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "region", nullable = true, length = 32)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 11)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "real_name", nullable = true, length = 32)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "papers_type", nullable = true)
    public Integer getPapersType() {
        return papersType;
    }

    public void setPapersType(Integer papersType) {
        this.papersType = papersType;
    }

    @Basic
    @Column(name = "identity_no", nullable = true, length = 18)
    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    @Basic
    @Column(name = "pprove_type", nullable = true)
    public Integer getPproveType() {
        return pproveType;
    }

    public void setPproveType(Integer pproveType) {
        this.pproveType = pproveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AUser aUser = (AUser) o;

        if (aid != aUser.aid) return false;
        if (userName != null ? !userName.equals(aUser.userName) : aUser.userName != null) return false;
        if (name != null ? !name.equals(aUser.name) : aUser.name != null) return false;
        if (tel != null ? !tel.equals(aUser.tel) : aUser.tel != null) return false;
        if (password != null ? !password.equals(aUser.password) : aUser.password != null) return false;
        if (flag != null ? !flag.equals(aUser.flag) : aUser.flag != null) return false;
        if (ioc != null ? !ioc.equals(aUser.ioc) : aUser.ioc != null) return false;
        if (sex != null ? !sex.equals(aUser.sex) : aUser.sex != null) return false;
        if (province != null ? !province.equals(aUser.province) : aUser.province != null) return false;
        if (city != null ? !city.equals(aUser.city) : aUser.city != null) return false;
        if (region != null ? !region.equals(aUser.region) : aUser.region != null) return false;
        if (birthday != null ? !birthday.equals(aUser.birthday) : aUser.birthday != null) return false;
        if (address != null ? !address.equals(aUser.address) : aUser.address != null) return false;
        if (email != null ? !email.equals(aUser.email) : aUser.email != null) return false;
        if (phone != null ? !phone.equals(aUser.phone) : aUser.phone != null) return false;
        if (qq != null ? !qq.equals(aUser.qq) : aUser.qq != null) return false;
        if (realName != null ? !realName.equals(aUser.realName) : aUser.realName != null) return false;
        if (papersType != null ? !papersType.equals(aUser.papersType) : aUser.papersType != null) return false;
        if (identityNo != null ? !identityNo.equals(aUser.identityNo) : aUser.identityNo != null) return false;
        if (pproveType != null ? !pproveType.equals(aUser.pproveType) : aUser.pproveType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (ioc != null ? ioc.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (papersType != null ? papersType.hashCode() : 0);
        result = 31 * result + (identityNo != null ? identityNo.hashCode() : 0);
        result = 31 * result + (pproveType != null ? pproveType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AUser{" +
                "aid=" + aid +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                ", flag='" + flag + '\'' +
                ", ioc='" + ioc + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", realName='" + realName + '\'' +
                ", papersType=" + papersType +
                ", identityNo='" + identityNo + '\'' +
                ", pproveType=" + pproveType +
                '}';
    }
}
