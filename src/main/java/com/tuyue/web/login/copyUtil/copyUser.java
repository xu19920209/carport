package com.tuyue.web.login.copyUtil;


import com.tuyue.pojo.AUser;
import com.tuyue.util.Setting;

/**
 * Created by Yui on 2017/9/23.
 */
public class copyUser {

    public static AUser copy(AUser user,AUser beUpdatedUser) {
        if(user.getAid()!=0){
            beUpdatedUser.setAid(user.getAid());
        }
        if(user.getPassword()!=null&&!"".equals(user.getPassword())){
            beUpdatedUser.setPassword(user.getPassword());
        }
        if(user.getAddress()!=null&&!"".equals(user.getAddress())){
            beUpdatedUser.setAddress(user.getAddress());
        }
        if(user.getBirthday()!=null&&!"".equals(user.getBirthday())){
            beUpdatedUser.setBirthday(user.getBirthday());
        }
        if(user.getCity()!=null&&!"".equals(user.getBirthday())){
            beUpdatedUser.setCity(user.getCity());
        }
        if(user.getEmail()!=null&&!"".equals(user.getEmail())){
            beUpdatedUser.setEmail(user.getEmail());
        }
        if(user.getFlag()!=null&&!"".equals(user.getFlag())){
            beUpdatedUser.setFlag(user.getFlag());
        }
        if(user.getIdentityNo()!=null&&!"".equals(user.getIdentityNo())){
            beUpdatedUser.setIdentityNo(user.getIdentityNo());
        }
        if(user.getIoc()!=null&&!"".equals(user.getIoc())){
            System.out.println("user.getIoc()11111:::"+user.getIoc());
            beUpdatedUser.setIoc(user.getIoc());
        }else{
            //注 后期要判断企业还是个人用户
            System.out.println("user.getIoc()22222:::"+user.getIoc());
            System.out.println("Setting.PERSONAGEPIC:::"+Setting.PERSONAGEPIC);
            beUpdatedUser.setIoc(Setting.PERSONAGEPIC);
        }
        if(user.getName()!=null&&!"".equals(user.getName())){
            beUpdatedUser.setName(user.getName());
        }
        if(user.getPapersType()!=null&&!"".equals(user.getPapersType())){
            beUpdatedUser.setPapersType(user.getPapersType());
        }
        if(user.getPproveType()!=null&&!"".equals(user.getPproveType())){
            beUpdatedUser.setPproveType(user.getPproveType());
        }
        if(user.getPhone()!=null&&!"".equals(user.getPhone())){
            beUpdatedUser.setPhone(user.getPhone());
        }
        if(user.getProvince()!=null&&!"".equals(user.getProvince())){
            beUpdatedUser.setProvince(user.getProvince());
        }
        if(user.getQq()!=null&&!"".equals(user.getQq())){
            beUpdatedUser.setQq(user.getQq());
        }
        if(user.getRealName()!=null&&!"".equals(user.getRealName())){
            beUpdatedUser.setRealName(user.getRealName());
        }
        if(user.getRegion()!=null&&!"".equals(user.getRegion())){
            beUpdatedUser.setRegion(user.getRegion());
        }
        if(user.getSex()!=null&&!"".equals(user.getSex())){
            beUpdatedUser.setSex(user.getSex());
        }
        if(user.getUserName()!=null&&!"".equals(user.getUserName())){
            beUpdatedUser.setUserName(user.getUserName());
        }
        if(user.getTel()!=null&&!"".equals(user.getTel())){
            beUpdatedUser.setTel(user.getTel());
        }
        return beUpdatedUser;
    }

}
