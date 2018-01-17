package com.tuyue.web.comment.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.DReviewBack;
import com.tuyue.util.Page;
import com.tuyue.web.comment.bean.CommentBean;
import com.tuyue.web.comment.bean.Commentstra;
import com.tuyue.web.comment.biz.IDReviewBackBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@Service
public class DReviewBackBizImpl implements IDReviewBackBiz {

    @Autowired
    private IBaseDao<DReviewBack> dReviewBackIBaseDao;

    @Autowired
    private IBaseDao<AUser> aUserIBaseDao;

    @Override
    public boolean add(DReviewBack dReviewBack) throws Exception {
        //评论时间
        dReviewBack.setReviewTime(new Timestamp((new Date()).getTime()) );
        //是否认证
        AUser aUser = aUserIBaseDao.getOne(AUser.class, dReviewBack.getAid());
        dReviewBack.setIsApprove(aUser.getPproveType());
        //用户名
        dReviewBack.setUserName(aUser.getUserName());
        //头像
        dReviewBack.setUserImg(aUser.getIoc());


        int i = dReviewBackIBaseDao.save(dReviewBack);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public Page<DReviewBack> list(Integer currentPage,Integer pageSize, Integer bid,Integer reviewType) throws SQLException {
        String hql="from DReviewBack where bid="+bid;
        String chql="SELECT count(*) from DReviewBack where bid="+bid;
        StringBuffer sb = new StringBuffer(hql);
        StringBuffer csb = new StringBuffer(chql);
        if (reviewType!=null) {
            sb.append(" and reviewType="+reviewType);
            csb.append(" and reviewType="+reviewType);
        }
        sb.append(" ORDER BY reviewTime DESC");
        csb.append(" ORDER BY reviewTime DESC");
        out.println(sb.toString());
        out.println(csb.toString());
        Page<DReviewBack> page = dReviewBackIBaseDao.findPage(currentPage, pageSize, sb.toString(), csb.toString());
        return page;
    }

    @Override
    public Commentstra getStar(Integer bid) throws Exception {
        Commentstra commentstra = new Commentstra();
        long sumcount = dReviewBackIBaseDao.findCount("SELECT count(*) from DReviewBack where bid=" + bid);
        long okcount = dReviewBackIBaseDao.findCount("SELECT count(*) from DReviewBack where bid=" + bid+" and reviewType=3");
        List<DReviewBack> list = dReviewBackIBaseDao.findList("from DReviewBack where bid=" + bid);
        Double zong=0.0;
        Double sever=0.0;
        for (DReviewBack dReviewBack : list) {
            zong+=dReviewBack.getTrueGrade();
            sever+=dReviewBack.getServeGrade();
        }
        DecimalFormat format = new DecimalFormat("0.00");
        //好评率
        Double realStra=0.0;
        Double severStra=0.0;
        Double goodComment=0.0;
        if (list.size()>0) {
            System.out.println("zong"+zong);
            System.out.println("sever"+sever);
            System.out.println("list.size()"+list.size());
            realStra=zong/(list.size()*5);
            severStra=sever/(list.size()*5);
        }
        if (sumcount>0) {
            String s = format.format((float) okcount / sumcount);
            out.println(s);
            goodComment=Double.parseDouble(s)*100;
            System.out.println(goodComment);
        }
        commentstra.setGoodComment(goodComment);
        commentstra.setRealStra(realStra*5);
        commentstra.setServeStra(severStra*5);
        return commentstra;
    }

    @Override
    public CommentBean getCommentcount(Integer bid) {
        long okcount = dReviewBackIBaseDao.findCount("SELECT count(*) from DReviewBack where bid=" + bid+" and reviewType=1");
        long middleCount = dReviewBackIBaseDao.findCount("SELECT count(*) from DReviewBack where bid=" + bid+" and reviewType=2");
        long badCount = dReviewBackIBaseDao.findCount("SELECT count(*) from DReviewBack where bid=" + bid+" and reviewType=3");
        CommentBean commentBean = new CommentBean();
        commentBean.setBadCount(badCount);
        commentBean.setGoodCount(okcount);
        commentBean.setMiddleCount(middleCount);
        return commentBean;
    }
}
