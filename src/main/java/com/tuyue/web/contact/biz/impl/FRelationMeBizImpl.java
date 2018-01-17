package com.tuyue.web.contact.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.FRelationMe;
import com.tuyue.web.contact.biz.IFRelationMeBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@Service
public class FRelationMeBizImpl implements IFRelationMeBiz {

    @Autowired
    private IBaseDao<FRelationMe> fRelationMeIBaseDao;
    @Override
    public boolean add(FRelationMe fRelationMe) {
        fRelationMe.setRelationTime(new Timestamp(new Date().getTime()));
        int i = fRelationMeIBaseDao.save(fRelationMe);
        if (i>0){
            return true;
        }
        return false;
    }
}
