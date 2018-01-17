package com.tuyue.web.feedback.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.CCarportImg;
import com.tuyue.pojo.IFeedback;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.web.feedback.bean.IFeedBean;
import com.tuyue.web.feedback.biz.IIFeedbackBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/22.
 * @Modified By:
 */
@Service
public class IFeedbackBizImpl implements IIFeedbackBiz {
    @Autowired
    private IBaseDao<IFeedback> iFeedbackIBaseDao;
    @Autowired
    private IBaseDao<CCarportImg> cCarportImgIBaseDao;


    @Override
    public boolean add(IFeedBean iFeedBean) throws Exception {
        IFeedback iFeedback = ObjectCopyUtil.copy(iFeedBean, IFeedBean.class, IFeedback.class);
        iFeedback.setiTime(new Timestamp(new Date().getTime()));
        int i = iFeedbackIBaseDao.save(iFeedback);
        if (i>0){
            if (iFeedBean.getcCarportImgs().size()>0) {
                ArrayList<CCarportImg> cCarportImgs = new ArrayList<CCarportImg>();
                for (CCarportImg cCarportImg : iFeedBean.getcCarportImgs()) {
                    cCarportImg.setBid(i);
                    cCarportImg.setImgType(2);
                    cCarportImgs.add(cCarportImg);
                }
                int batchSave = cCarportImgIBaseDao.batchSave(cCarportImgs);
                if (batchSave==iFeedBean.getcCarportImgs().size()) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
