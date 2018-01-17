package com.tuyue.web.login.biz.impl;

import com.tuyue.bean.Result;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.TAccesstoken;
import com.tuyue.util.ResultUtil;
import com.tuyue.util.Tools;
import com.tuyue.web.login.biz.IAUserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 王晨 on 2017/9/20.
 */
@Service("AUserBiz")
@Transactional
public class AUserBizImpl implements IAUserBiz{

    @Autowired
    private IBaseDao<AUser> AUserDao;
    @Autowired
    private IBaseDao<TAccesstoken> TaccessTokenDao;

    @Override
    public Result loginByPassword(String username, String password) throws Exception {
            //200
            AUser  user = AUserDao.findOne("from AUser where userName = '" + username + "'");
            if(user==null){
                user = AUserDao.findOne("from AUser where tel = '" + username + "'");
                if(user == null){
                return ResultUtil.error(3,"用户名/手机号不存在");
                }else{
                    if (user.getPassword().equals(password)) {
                        TAccesstoken accessToken = findAcessToken(user.getAid());
                        int save =0;
                        int randomNum = Tools.getRandomNum();
                        //如果accessToken 为NULL
                        if(accessToken==null){

                        TAccesstoken accessToken1 = new TAccesstoken();
                        accessToken1.setAid(user.getAid());
                        accessToken1.setAccessToken(String.valueOf(randomNum));
                        save = TaccessTokenDao.save(accessToken1);

                        }else{
                            accessToken.setAccessToken(String.valueOf(randomNum));
                            boolean b = updateAcessToken(accessToken);
                            if(b){
                                save=1;
                            }

                        }
                        if(save>0){

                            return ResultUtil.success(String.valueOf(randomNum));
                        }else{
                            return ResultUtil.error(2,"生成accessToken失败");
                        }

                    }else{
                        return ResultUtil.error(2,"手机号登录密码错误");
                    }
                }
            }else{
                if (user.getPassword().equals(password)) {
                    //return ResultUtil.success(user);
                    if (user.getPassword().equals(password)) {
                        TAccesstoken accessToken = findAcessToken(user.getAid());
                        int save =0;
                        int randomNum = Tools.getRandomNum();
                        //如果accessToken 为NULL
                        if(accessToken==null){

                            TAccesstoken accessToken1 = new TAccesstoken();
                            accessToken1.setAid(user.getAid());
                            accessToken1.setAccessToken(String.valueOf(randomNum));
                            save = TaccessTokenDao.save(accessToken1);

                        }else{
                            accessToken.setAccessToken(String.valueOf(randomNum));
                            boolean b = updateAcessToken(accessToken);
                            if(b){
                                save=1;
                            }

                        }

                        if(save>0){

                            return ResultUtil.success(String.valueOf(randomNum));
                        }else{
                            return ResultUtil.error(2,"生成accessToken失败");
                        }

                    }else{
                        return ResultUtil.error(2,"手机号登录密码错误");
                    }
                }else{
                    return ResultUtil.error(2,"用户名登录密码错误");
                }
            }
    }


    @Override
    public Result mobileIsExist(String phone) throws Exception {
        AUser user = AUserDao.findOne("from AUser where tel = '" + phone + "'");
        if(user==null){
             //数据库没有
            return ResultUtil.success(1,"手机号未注册",0);
        }else{
            //数据库有
            return  ResultUtil.success(1,"手机号已存在",1);
        }
    }

    @Override
    public Result userNameIsExist(String userName) throws Exception {
        AUser user = AUserDao.findOne("from AUser where userName = '" + userName + "'");
        if(user==null){
            //数据库没有
            return ResultUtil.success(1,"用户名未注册",0);
        }else{
            //数据库有
            return  ResultUtil.success(1,"用户名已存在",1);
        }
    }

    @Override
    public AUser findByPhone(String phone) throws Exception{
        AUser user = AUserDao.findOne("from AUser where tel = '" + phone + "'");
        return user;
    }

    @Override
    public boolean update(AUser user) throws  Exception{
        return AUserDao.update(user);
    }
    @Override
    public AUser findById(Integer id) throws  Exception{
        return AUserDao.getOne(AUser.class,id);
    }

    public int addUser(AUser user){
        return AUserDao.save(user);
    }

    @Override
    public String addAcessToken(Integer aid) {
        TAccesstoken accessToken = new TAccesstoken();
        int randomNum = Tools.getRandomNum();
        accessToken.setAid(aid);
        accessToken.setAccessToken(String.valueOf(randomNum));
        int save = TaccessTokenDao.save(accessToken);
        System.out.println("");
        if(save>0){
            return String.valueOf(randomNum);
        }else{
            return "2";
        }
    }

    public AUser findByAcessToken(String accessToken) throws  Exception{
        TAccesstoken ac = TaccessTokenDao.findOne("from TAccesstokenEntity where accessToken = '" + accessToken + "'");
        if(ac!=null){
            AUser user = findById(ac.getAid());
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
    }
