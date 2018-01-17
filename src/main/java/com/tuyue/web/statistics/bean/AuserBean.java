package com.tuyue.web.statistics.bean;

import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description: 统计bean
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
public class AuserBean {
    private int aid;
    private String name;
    private String ioc;//头像
    private Integer pproveType; //是否实名认证
    private Long publish;//发表数
    private Long visitor;//访问数
    private String tel; //手机号
    private String flag;//个人用户还是企业用户
    private String sex;//性别
    private Timestamp lookTime;//联系时间

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public Integer getPproveType() {
        return pproveType;
    }

    public void setPproveType(Integer pproveType) {
        this.pproveType = pproveType;
    }

    public Long getPublish() {
        return publish;
    }

    public void setPublish(Long publish) {
        this.publish = publish;
    }

    public Long getVisitor() {
        return visitor;
    }

    public void setVisitor(Long visitor) {
        this.visitor = visitor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getLookTime() {
        return lookTime;
    }

    public void setLookTime(Timestamp lookTime) {
        this.lookTime = lookTime;
    }
}
