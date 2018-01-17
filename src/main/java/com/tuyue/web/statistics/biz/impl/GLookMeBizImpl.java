package com.tuyue.web.statistics.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.BCarportOut;
import com.tuyue.pojo.FRelationMe;
import com.tuyue.pojo.GLookMe;
import com.tuyue.util.Page;
import com.tuyue.web.statistics.bean.AuserBean;
import com.tuyue.web.statistics.biz.IGLookMeBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@Service
public class GLookMeBizImpl implements IGLookMeBiz{
    @Autowired
    public IBaseDao<GLookMe> gLookMeIBaseDao;
    @Autowired
    public IBaseDao<AUser> aUserIBaseDao;
    @Autowired
    public IBaseDao<BCarportOut> bCarportOutIBaseDao;
    @Autowired
    public IBaseDao<FRelationMe> fRelationMeIBaseDao;

    @Override
    public Page<AuserBean> list(Integer currentPage, Integer pageSize, Integer flag,Integer aid) throws Exception {
        Page<AuserBean> auserBeanPage = new Page<AuserBean>();
        ArrayList<AuserBean> auserBeans = new ArrayList<AuserBean>();
        String hql="";
        String chql="";

        switch (flag) {
            //谁看过我
            /**
             * 1，通过登陆ID查询G表得到list
             * 2，for得到谁访问过我的用户ID
             * 3，通过用户ID查询用户表得到用户信息赋值给bean
             */
            case 1:
                hql="from GLookMe where mainId="+aid;
                chql="SELECT count(*) from GLookMe where mainId="+aid;
                StringBuffer sb = new StringBuffer(hql);
                StringBuffer csb = new StringBuffer(chql);
                out.println(hql);
                out.println(chql);
                Page<GLookMe> gLookMePage = gLookMeIBaseDao.findPage(currentPage, pageSize, sb.toString(), csb.toString());

                for (GLookMe gLookMe : gLookMePage.getList()) {
                    AuserBean auserBean = new AuserBean();
                    AUser aUser = aUserIBaseDao.getOne(AUser.class, gLookMe.getLookId());
                    auserBean.setAid(aUser.getAid());
                    auserBean.setPproveType(aUser.getPproveType());
                    auserBean.setIoc(aUser.getIoc());
                    auserBean.setName(aUser.getUserName());
                    auserBean.setSex(aUser.getSex());
                    auserBean.setLookTime(gLookMe.getLookTime());
                    auserBean.setFlag(aUser.getFlag());
                    long bCarportOutIBaseDaoCount = bCarportOutIBaseDao.findCount("SELECT count(*) from BCarportOut where aid=" + gLookMe.getLookId());
                    //发表数
                    auserBean.setPublish(bCarportOutIBaseDaoCount);
                    //访问量
                    long gLookMeIBaseDaoCount = gLookMeIBaseDao.findCount("SELECT count(*) from GLookMe where mainId=" + gLookMe.getLookId());
                    auserBean.setVisitor(gLookMeIBaseDaoCount);
                    auserBeans.add(auserBean);
                }

                auserBeanPage.setList(auserBeans);
                auserBeanPage.setTotal(gLookMePage.getTotal());
                auserBeanPage.setPageSize(gLookMePage.getPageSize());
                auserBeanPage.setallPage(gLookMePage.getAllpage());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            //谁联系我
            /**
             * 1通过用户ID查询发布的b所有车位
             * 2，for得到车位表中bid通过车位表中bid查询f表中list
             * 3，得到去重
             */
            case 5:
                List<BCarportOut> bCarportOuts = bCarportOutIBaseDao.findList("from BCarportOut where aid=" + aid);
                StringBuffer buffer = new StringBuffer("");
                int i=0;
                for (BCarportOut bCarportOut : bCarportOuts) {
                    i++;
                    buffer.append(bCarportOut.getBid());
                    if (i<bCarportOuts.size()) {
                        buffer.append(",");
                    }
                }
                System.out.println("from FRelationMe where bid in (" + buffer.toString() + ") GROUP BY aid");
                System.out.println("SELECT count(*) from FRelationMe where bid in (" + buffer.toString() + ") GROUP BY aid");
                Page<FRelationMe> fRelationMePage = fRelationMeIBaseDao.findPage(currentPage, pageSize, "from FRelationMe where bid in (" + buffer.toString() + ") GROUP BY aid", "SELECT count(*) from FRelationMe where bid in (" + buffer.toString() + ") GROUP BY aid");
                for (FRelationMe fRelationMe : fRelationMePage.getList()) {

                    AuserBean auserBean = new AuserBean();
                    AUser aUser = aUserIBaseDao.getOne(AUser.class, fRelationMe.getAid());
                    auserBean.setAid(aUser.getAid());
                    auserBean.setPproveType(aUser.getPproveType());
                    auserBean.setIoc(aUser.getIoc());
                    auserBean.setName(aUser.getUserName());
                    auserBean.setSex(aUser.getSex());
                    auserBean.setLookTime(fRelationMe.getRelationTime());
                    auserBean.setFlag(aUser.getFlag());
                    auserBean.setTel(aUser.getTel());
                    long bCarportOutIBaseDaoCount = bCarportOutIBaseDao.findCount(" SELECT count(*) from BCarportOut where aid=" + fRelationMe.getAid());
                    //发表数
                    auserBean.setPublish(bCarportOutIBaseDaoCount);
                    //访问量
                    long gLookMeIBaseDaoCount = gLookMeIBaseDao.findCount("SELECT count(*) from GLookMe where mainId=" + fRelationMe.getAid());
                    auserBean.setVisitor(gLookMeIBaseDaoCount);
                    auserBeans.add(auserBean);
                }
                auserBeanPage.setList(auserBeans);
                auserBeanPage.setTotal(fRelationMePage.getTotal());
                auserBeanPage.setPageSize(fRelationMePage.getPageSize());
                auserBeanPage.setallPage(fRelationMePage.getAllpage());
                break;
            default:
                break;
        }
        return auserBeanPage;
    }
}
