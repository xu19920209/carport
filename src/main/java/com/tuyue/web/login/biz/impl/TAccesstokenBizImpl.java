package com.tuyue.web.login.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.TAccesstoken;
import com.tuyue.util.Tools;
import com.tuyue.web.login.biz.ITAccesstokenBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Yui on 2017/9/22.
 */
@Service
@Transactional
public class TAccesstokenBizImpl implements ITAccesstokenBiz{
    @Autowired
    private IBaseDao<AUser> AUserDao;
    @Autowired
    private IBaseDao<TAccesstoken> TaccessTokenDao;

    @Override
    public String addAcessToken(Integer aid) {
        TAccesstoken accessToken = new TAccesstoken();
        int randomNum = Tools.getRandomNum();
        accessToken.setAid(aid);
        accessToken.setAccessToken(String.valueOf(randomNum));
        int save = TaccessTokenDao.save(accessToken);
        if(save>0){
            return String.valueOf(randomNum);
        }else{
            return "2";
        }
    }

    public AUser findByAcessToken(String accessToken) throws  Exception{
        TAccesstoken ac = TaccessTokenDao.findOne("from TAccesstoken where accessToken = '" + accessToken + "'");
        if(ac!=null){
            AUser user = AUserDao.getOne(AUser.class,ac.getAid());
            if(user!=null){
                return user;
            }else{
                return null;
            }

        }else{
            return null;
        }
    }

    public TAccesstoken findAcessToken(Integer aid) throws  Exception{
        TAccesstoken ac = TaccessTokenDao.findOne("from TAccesstoken where aid = '" + aid + "'");
        return ac;
    }

    public boolean updateAcessToken(TAccesstoken accesstoken) throws  Exception{
        return TaccessTokenDao.update(accesstoken);
    }

    @Override
    public TAccesstoken findAcessToken(String token) throws Exception {

        return TaccessTokenDao.findOne("from TAccesstoken where accessToken = '" + token + "'");
    }
}
