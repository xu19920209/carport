package com.tuyue.web.publish.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.BCarportOut;
import com.tuyue.pojo.CCarportImg;
import com.tuyue.pojo.GLookMe;
import com.tuyue.pojo.HmyCollect;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import com.tuyue.web.publish.biz.IBCarportOutBiz;
import com.tuyue.web.publish.biz.bean.BCarportOutImgBean;
import com.tuyue.web.publish.biz.bean.BCarportOutlistBean;
import com.tuyue.web.publish.biz.bean.ListBCarportOutByAidBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@Service
public class BCarportOutBizImpl implements IBCarportOutBiz {

    @Autowired
    private IBaseDao<BCarportOut> bCarportOutIBaseDao;
    @Autowired
    private IBaseDao<CCarportImg> cCarportImgIBaseDao;
    @Autowired
    private IBaseDao<HmyCollect> hmyCollectIBaseDao;
    @Autowired
    private IBaseDao<GLookMe> gLookMeIBaseDao;

    @Override
    public boolean PublishHire( BCarportOutImgBean bCarportOutImgBean) throws Exception {
        BCarportOut bCarportOut = ObjectCopyUtil.copy(bCarportOutImgBean, BCarportOutImgBean.class, BCarportOut.class);
        // 点击量
        bCarportOut.setPointNum(0l);
        //是否上架
        bCarportOut.setIsPutaway(1);
        //降价
        bCarportOut.setLowerMoney(bCarportOut.getRental());
        //是否降价
        bCarportOut.setIsLowerMoney(2);
        bCarportOut.setIssueTime(new Timestamp(new Date().getTime()));
        // TODO: 2017/9/30  
        //保留两位小数
        out.println(bCarportOut.getRental());
        BigDecimal bg = new BigDecimal(bCarportOut.getRental());
        double f = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        bCarportOut.setRental(f);
        System.out.println(bCarportOut.getRental());
        int i = bCarportOutIBaseDao.save(bCarportOut);
        if (i > 0) {
            List<CCarportImg> cCarportImgs = bCarportOutImgBean.getcCarportImgs();
            for (CCarportImg cCarportImg : cCarportImgs) {
                //设置出租出售图片type
                cCarportImg.setBid(i);
                cCarportImg.setImgType(1);
            }
            int batchSave = cCarportImgIBaseDao.batchSave(cCarportImgs);
            if (batchSave == cCarportImgs.size()) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @param carportType    车位类型（1露天2机械3地下）
     * @param rentalscope    租金范围
     * @param region         区
     * @param enterpriseType 个人企业flag（1个人2企业）
     * @param rentType       出租转让（1出租2转让）
     * @Author: 王金海
     * @Description: 分页查询list车位出租转租
     * @Date: 10:42 2017/9/20
     */
    @Override
    public Page<BCarportOutlistBean> list(String province, String city, Integer currentPage, Integer pageSize, Integer carportType, String rentalscope, String region, Integer enterpriseType, Integer rentType,String str,Integer isSale) throws Exception {
        city = new String(city.getBytes("iso8859-1"),"utf-8");
//        String hql = "from BCarportOut where rentType=" + rentType + " and province='" + province + "' and city='" + city + "' and isSale="+isSale;
        String hql = "from BCarportOut where rentType=" + rentType + "  and city like '%" + city + "%' and isSale="+isSale;
//        String chql = "SELECT count(*) FROM BCarportOut where rentType=" + rentType + " and province='" + province + "' and city='" + city + "' and isSale="+isSale;
        String chql = "SELECT count(*) FROM BCarportOut where rentType=" + rentType + " and city like '%" + city + "%' and isSale="+isSale;
        StringBuffer sb = new StringBuffer(hql);
        StringBuffer csb = new StringBuffer(chql);
        if (carportType != null) {
            sb.append(" and carportType=" + carportType);
            csb.append(" and carportType=" + carportType);
        }

        if (enterpriseType != null) {
            sb.append(" and enterpriseType=" + enterpriseType);
            csb.append(" and enterpriseType=" + enterpriseType);
        }
        if (region != null) {
            region = new String(region.getBytes("iso8859-1"), "utf-8");
            sb.append(" and region='" + region + "'");
            csb.append(" and region='" + region + "'");
        }
        if (rentalscope != null) {
            sb.append(" and rental " + rentalscope + "");
            csb.append(" and rental " + rentalscope + "");
        }
        if(str!=null){
            str = new String(str.getBytes("iso8859-1"), "utf-8");
            sb.append(" and (housesName like '%"+str+"%' or businessCircles like '%"+str+"%' or  housesAdress like '%"+str+"%' or title like '%"+str+"%')");
            csb.append(" and (housesName like '%"+str+"%' or businessCircles like '%"+str+"%' or  housesAdress like '%"+str+"%' or title like '%"+str+"%')");
        }
        sb.append(" ORDER BY issueTime DESC");
        csb.append(" ORDER BY issueTime DESC");
        out.println(sb.toString());
        Page<BCarportOut> page = bCarportOutIBaseDao.findPage(currentPage, pageSize, sb.toString(), csb.toString());
        Page<BCarportOutlistBean> bCarportOutlistBeanPage = new Page<BCarportOutlistBean>();
        ArrayList<BCarportOutlistBean> bCarportOutlistBeans = new ArrayList<BCarportOutlistBean>();
        for (BCarportOut bCarportOut : page.getList()) {
            // TODO: 2017/9/30
            System.out.println("1111111111111111111111111111111111");
            System.out.println(bCarportOut.getRental());
            BCarportOutlistBean bCarportOutlistBean = ObjectCopyUtil.copy(bCarportOut, BCarportOut.class, BCarportOutlistBean.class);
            long picCount = cCarportImgIBaseDao.findCount("SELECT count(*) from CCarportImg where bid=" + bCarportOut.getBid() + " and imgType=1");
            //图片数量
            bCarportOutlistBean.setPicCount(picCount);
            long collectSumCount = hmyCollectIBaseDao.findCount("SELECT count(*) from HmyCollect where bid=" + bCarportOut.getBid());
            //收藏数
            bCarportOutlistBean.setCollectSum(collectSumCount);
            //关注数
            // TODO: 2017/9/20 暂时没有关注默认给0
            bCarportOutlistBean.setAttentionSum(1L);
            //虚假数
            // TODO: 2017/9/20 暂时没有虚假数默认给0
            bCarportOutlistBean.setShamSum(1L);
            System.out.println(bCarportOutlistBean.toString());
            bCarportOutlistBeans.add(bCarportOutlistBean);
        }
        bCarportOutlistBeanPage.setList(bCarportOutlistBeans);
        bCarportOutlistBeanPage.setTotal(page.getTotal());
        bCarportOutlistBeanPage.setPageSize(page.getPageSize());
        bCarportOutlistBeanPage.setallPage(page.getAllpage());
        return bCarportOutlistBeanPage;
    }

    @Override
    public BCarportOutImgBean getInfo(Integer bid,Integer baid,Integer aaid) throws Exception {
        if (aaid!=null){
            GLookMe lookMe = gLookMeIBaseDao.findOne("from GLookMe where lookId=" + aaid);
            if (lookMe!=null) {
                lookMe.setLookTime(new Timestamp(new Date().getTime()));
                boolean b = gLookMeIBaseDao.update(lookMe);
                if (b) {
                    out.println("修改访问时间成功");
                }
            }else {
                GLookMe gLookMe = new GLookMe();
                gLookMe.setLookTime(new Timestamp(new Date().getTime()));
                gLookMe.setLookId(aaid);
                gLookMe.setMainId(baid);
                int i = gLookMeIBaseDao.save(gLookMe);
                if (i>0) {
                    out.println("增加访问记录");
                }
            }
        }

        BCarportOut bCarportOut = bCarportOutIBaseDao.getOne(BCarportOut.class, bid);
        bCarportOut.setPointNum(bCarportOut.getPointNum() + 1);

        boolean b = bCarportOutIBaseDao.update(bCarportOut);
        BCarportOutImgBean bCarportOutImgBean = ObjectCopyUtil.copy(bCarportOut, BCarportOut.class, BCarportOutImgBean.class);
        List<CCarportImg> cCarportImgList = cCarportImgIBaseDao.findList("from CCarportImg where bid=" + bCarportOut.getBid());
        bCarportOutImgBean.setcCarportImgs(cCarportImgList);
        long collectSumCount = hmyCollectIBaseDao.findCount("SELECT count(*) from HmyCollect where bid=" + bCarportOut.getBid());
        //收藏数
        bCarportOutImgBean.setCollectSum(collectSumCount);
        //关注数
        // TODO: 2017/9/20 暂时没有关注默认给0
        bCarportOutImgBean.setAttentionSum(1L);
        //虚假数
        // TODO: 2017/9/20 暂时没有虚假数默认给0
        bCarportOutImgBean.setShamSum(1L);

        return bCarportOutImgBean;
    }

    @Override
    public List<BCarportOut> findListByMoble(String mobile) throws  Exception{
        return this.bCarportOutIBaseDao.findList("from BCarportOut where pjone = '"+mobile+"'");
    }

    @Override
    public boolean updateBCarportOut(BCarportOut bCarportOut) throws Exception {
        out.println("bCarportOut.getBid()::"+bCarportOut.getBid());
        return this.bCarportOutIBaseDao.update(bCarportOut);
    }

    @Override
    public Page<ListBCarportOutByAidBean> listBCarportOutByAid(Integer currentPage, Integer pageSize, Integer aid, Integer rentType, Integer isPutaway)  throws  Exception {

        StringBuffer sb = new StringBuffer(" from BCarportOut b where aid = "+aid);
        StringBuffer sbCount =new StringBuffer("select count(*) from BCarportOut where  aid = "+aid);

         if(rentType!=null){
            sb.append(" and rentType = "+rentType);
            sbCount.append(" and rentType = "+rentType);
         }

         if(isPutaway!=null){
            sb.append(" and isPutaway = "+isPutaway);
            sbCount.append(" and isPutaway = "+isPutaway);
         }
       // order by is_putaway asc,issue_Time desc
        sb.append(" order by is_putaway asc, issueTime desc");
        sbCount.append(" order by is_putaway asc,issueTime desc");
        //ListBCarportOutByAidBean
        Page<BCarportOut> pages = bCarportOutIBaseDao.findPage(currentPage, pageSize, sb.toString(), sbCount.toString());
        List<ListBCarportOutByAidBean> beans = new ArrayList<ListBCarportOutByAidBean>();
        for (BCarportOut bCarportOut : pages.getList())
        {
            ListBCarportOutByAidBean bean = null;
            try {
                bean = ObjectCopyUtil.copy(bCarportOut, BCarportOut.class, ListBCarportOutByAidBean.class);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            beans.add(bean);
        }
        Page<ListBCarportOutByAidBean> listBCarportOutByAidBeanPage = new Page<ListBCarportOutByAidBean>();
        listBCarportOutByAidBeanPage.setList(beans);
        listBCarportOutByAidBeanPage.setTotal(pages.getTotal());
        listBCarportOutByAidBeanPage.setPageSize(pages.getPageSize());
        listBCarportOutByAidBeanPage.setallPage(pages.getAllpage());

        return listBCarportOutByAidBeanPage;
    }

    @Override
    public BCarportOut findById(int bid) throws Exception{

        return this.bCarportOutIBaseDao.getOne(BCarportOut.class,bid);
    }

    @Override
    public int delete(int bid) throws Exception {
        BCarportOut bcar = findById(bid);
        if(bcar==null){
            //bid查不到对象
            return 2;
        }
        boolean delete = bCarportOutIBaseDao.delete(bcar);
        if(delete){
            //删除成功
            return 1;
        }else{
            //删除失败
            return 3;
        }

    }

    @Override
    public boolean updateBCarportOutImgBean(BCarportOutImgBean bCarportOutImgBean) throws Exception {
        out.println("bCarportOut.getBid()::"+bCarportOutImgBean.getBid());
        BCarportOut bCarportOut = ObjectCopyUtil.copy(bCarportOutImgBean, BCarportOutImgBean.class, BCarportOut.class);
        // 点击量
        bCarportOut.setPointNum(0l);
        //是否上架
        bCarportOut.setIsPutaway(1);
        //是否降价
        bCarportOut.setIsLowerMoney(2);
       //降价


        bCarportOut.setIssueTime(new Timestamp(new Date().getTime()));
        boolean i = bCarportOutIBaseDao.update(bCarportOut);
        if (i) {
            List<CCarportImg> cCarportImgs = bCarportOutImgBean.getcCarportImgs();
            for (CCarportImg cCarportImg : cCarportImgs) {
                //设置出租出售图片type
                cCarportImg.setBid(bCarportOut.getBid());
                cCarportImg.setImgType(1);
            }
            boolean flag = cCarportImgIBaseDao.deleteAll(cCarportImgs);
            if(flag){
                int batchSave = cCarportImgIBaseDao.batchSave(cCarportImgs);
                if (batchSave == cCarportImgs.size()) {
                    return true;
                }
                return false;
            }else{
                return false;
            }

        }
        return false;

    }

    @Override
    public BCarportOutImgBean findByBid(int bid) throws Exception{

        BCarportOut BCarportOut = findById(bid);

        BCarportOutImgBean bean = ObjectCopyUtil.copy(BCarportOut, BCarportOut.class, BCarportOutImgBean.class);

        List<CCarportImg> list = cCarportImgIBaseDao.findList("from CCarportImg where bid ="+bid);

        bean.setcCarportImgs(list);

        return bean;
    }

}
