package com.tuyue.web.login.controller;

import com.tuyue.bean.Result;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.BCarportOut;
import com.tuyue.pojo.TAccesstoken;
import com.tuyue.util.*;
import com.tuyue.web.login.biz.IAUserBiz;
import com.tuyue.web.login.biz.ITAccesstokenBiz;
import com.tuyue.web.login.copyUtil.copyUser;
import com.tuyue.web.publish.biz.IBCarportOutBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 王晨 on 2017/9/20.
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("aUser")
public class AUserController {

    @Autowired
    private IAUserBiz userBiz;

    @Autowired
    private IBCarportOutBiz bCarBiz;

    @Autowired
    private ITAccesstokenBiz tokenBiz;

    /**
     * @Author: 王晨
     * @Description: 通过用户名/手机号进行登录
     * @param:userName 用户名/账号 password 密码
     * @Date: 9:39 2017/9/21
     */
    @PostMapping("loginByPassword")
    public Result loginByPassword(String userName ,String password) throws  Exception{
        Result result;
        if(userName!=null){
            if(password!=null){
                System.out.print("userName1111111::"+userName);
                userName = new String(userName.getBytes("iso8859-1"), "utf-8");
                System.out.print("userName222222::"+userName);
                System.out.print("password::"+password);
                result = userBiz.loginByPassword(userName, MD5.md5(password));
            }else{
                result = ResultUtil.error(2,"密码");
            }

        }else{
            result = ResultUtil.error(2,"请输入用户名");
        }

        return result;
    }

    /**
     * @Author: 王晨
     * @Description: 验证该手机是否注册
     * @param: phone 手机号
     * @Date: 9:39 2017/9/21
     */
    @GetMapping("mobileIsExist")
    public Result mobileIsExist(String phone) throws  Exception{
        Result result;
        if(phone==null){
            result = ResultUtil.error(2,"手机号不能为null");
        }else{
            result = userBiz.mobileIsExist(phone);
        }

        return result;
    }

    /**
     * @Author: 王晨
     * @Description: 验证该用户名是否注册
     * @param:userName 用户名
     * @Date: 9:40 2017/9/21
     */
    @GetMapping("userNameIsExist")
    public Result userNameIsExist(String userName) throws  Exception{
        Result result;
        if(userName==null){
            result = ResultUtil.error(2,"用户名不能为null");
        }else{
            userName = new String(userName.getBytes("iso8859-1"), "utf-8");
            result = userBiz.userNameIsExist(userName);
        }
        return result;
    }

    /**
     * @Author: 王晨
     * @Description: 手机获取验证码
     * @param:phone 手机号
     * @Date: 9:40 2017/9/21
     */
    @GetMapping("loginByMobileCode")
    public Result loginByMobileCode(final HttpSession session, final String phone){
        Result result ;
        if(phone!=null){
        /*-----------------------------------------------------------------------------------------------------------------------------*/
        //测试用代码
//        int randomNum = Tools.getRandomNum();
//        session.setAttribute("'"+phone+"'",randomNum);
//        System.out.println("randomNum::"+randomNum);
//        String num = "2";
 /*-----------------------------------------------------------------------------------------------------------------------------*/
        //正式代码
        String num = SmsUtil.sendVerificationCode(phone, session);
/*-----------------------------------------------------------------------------------------------------------------------------*/
        if(num.equals("2")){
            /*-------------------------------------------------------------------------------------------------*/
            //测试用代码
//            result = ResultUtil.success(1,"发送成功！",randomNum);
            /*-------------------------------------------------------------------------------------------------*/
            //正式代码
            result = ResultUtil.success(1,"发送成功！");
            /*-------------------------------------------------------------------------------------------------*/
        }else{
            result = ResultUtil.error(2,"发送失败！");
        }

        //定时器session 十五分钟 可以设置时间
            /**
             *
        int mins = 15;//时间设置 单位：分钟
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                session.removeAttribute("'"+phone+"'");
                System.out.println("checkCode删除成功");
                timer.cancel();
            }
        },mins*60*1000);
             */
        }else{
            result = ResultUtil.error(2,"手机号不能为null");
        }

        return result;
    };

    /**
     * @Author: 王晨
     * @Description: 忘记密码验证验证码
     * @param:phone手机号 code验证码
     * @Date: 9:40 2017/9/21
     */
    @PostMapping("checkcode")
    public Result checkCode(final HttpSession session,String phone,String code) throws Exception{
        Result result;
        if(phone!=null){
            if(code!=null){

            String sign = SmsUtil.verificationCode(session, phone, code);

            if(sign.equals("1")){
                //验证成功后生成一个随机验证码
                String num = phone+Tools.getRandomNum();
                session.setAttribute("'"+phone+"'",num);
                result = ResultUtil.success(1,"验证成功！",num);
            }else if(sign.equals("2")){
                result = ResultUtil.error(2,"验证码过期");
            }else if(sign.equals("3")){
                result = ResultUtil.error(2,"验证码不正确");
            }else{
                result = ResultUtil.error(2,"验证失败");
            }

        }else{
            result = ResultUtil.error(2,"验证码不能为null");
        }

        }else{
            result = ResultUtil.error(2,"手机号不能为null");
        }
        return result;
    }

    /**
     * @Author: 王晨
     * @Description: 验证验证码动态登录
     * @param:phone手机号 code验证码
     * @Date: 9:41 2017/9/21
     */
    @PostMapping("checkcodeAndLogin")
    public Result checkcodeAndLogin(HttpSession session,String phone,String code) throws Exception{

        String sign = SmsUtil.verificationCode(session, phone, code);

        Result result;
        if(sign.equals("1")){

            AUser user = userBiz.findByPhone(phone);

            TAccesstoken accessToken =  tokenBiz.findAcessToken(user.getAid());
            String s = "2";
            int randomNum = Tools.getRandomNum();
            //如果accessToken 为NULL 添加一个accessToken
            if(accessToken==null){

                s = tokenBiz.addAcessToken(user.getAid());

            }else{
                //如果accessToken 存在 添加一个accessToken
                accessToken.setAccessToken(String.valueOf(randomNum));
                boolean b = tokenBiz.updateAcessToken(accessToken);
                if(b){
                    s=String.valueOf(randomNum);
                }

            }

            if(s.equals("2")){
                result = ResultUtil.error(2,"未生成accessToken");
            }else{
                result = ResultUtil.success(s);
            }
        }else if(sign.equals("2")){
            result = ResultUtil.error(2,"验证码过期");
        }else if(sign.equals("3")){
            result = ResultUtil.error(2,"验证码不正确");
        }else{
            result = ResultUtil.error(2,"验证失败");
        }


        return result;
    }
    /**
     * @Author: 王晨
     * @Description: 忘记密码
     * @param:phone 手机号  password 忘记的密码
     * @Date: 9:41 2017/9/21
     */
    @PostMapping("updatePassword")
    public Result updatePassword(HttpSession session,String phone,String password,String code) throws  Exception{
        System.out.println("code::"+code);
        System.out.println("phone::"+phone);
        System.out.println("password::"+password);
        String sign = SmsUtil.verificationCode(session, phone, code);

        Result result;
        if(sign.equals("1")){
            AUser user = userBiz.findByPhone(phone);
            String pw = MD5.md5(password);
            if(user!=null){
                if(pw.equals(user.getPassword())){
                    result = ResultUtil.error(2,"请勿设置相同的密码");
                }else{
                    user.setPassword(pw);
                    boolean flag = userBiz.update(user);
                    if(flag){
                        result = ResultUtil.success(1,"重置成功");
                    }else{
                        result = ResultUtil.error(2,"数据库错误，重置失败");
                    }
                }
            }else{
                result = ResultUtil.error(2,"传的ID查不到对象");
            }
            return result;
        }else if(sign.equals("2")){
            result = ResultUtil.error(2,"验证码过期");
        }else if(sign.equals("3")){
            result = ResultUtil.error(2,"验证码不正确");
        }else{
            result = ResultUtil.error(2,"验证失败");
        }

        return result;
    }

    /**
     * @Author: 王晨
     * @Description:注册账号
     * @param:AUser 对象 code验证码
     * @Date: 9:42 2017/9/21
     */
    @RequestMapping("register")
    public Result register(HttpSession session,@RequestBody AUser user,String code) throws Exception{

        String sign = SmsUtil.verificationCode(session, user.getTel(), code);

        Result result;
        if(sign.equals("1")){
            user.setIoc(Setting.PERSONAGEPIC);
            user.setPproveType(2);
            String pw = MD5.md5(user.getPassword());
            user.setPassword(pw);
            int a = userBiz.addUser(user);

            if(a>0){

                List<BCarportOut> bCars = bCarBiz.findListByMoble(user.getTel());
                if(bCars.size()>0){
                    for (BCarportOut bcar: bCars) {
                        bcar.setAid(a);
                        boolean flag = bCarBiz.updateBCarportOut(bcar);
                        if(flag){
                            System.out.println("车位出租"+bcar.getBid()+"保存aid成功");
                        }else{
                            System.out.println("车位出租"+bcar.getBid()+"保存aid失败");
                        }
                    }
                }

                TAccesstoken accessToken =  tokenBiz.findAcessToken(user.getAid());
                String s = "2";
                int randomNum = Tools.getRandomNum();
                //如果accessToken 为NULL
                if(accessToken==null){

                    s = tokenBiz.addAcessToken(user.getAid());

                }else{
                    accessToken.setAccessToken(String.valueOf(randomNum));
                    boolean b = tokenBiz.updateAcessToken(accessToken);
                    if(b){
                        s=String.valueOf(randomNum);
                    }

                }

                if(s.equals("2")){
                    result = ResultUtil.error(2,"未生成accessToken");
                }else{
                    result = ResultUtil.success(s);
                }

            }else{
                result = ResultUtil.error(2,"未知错误，注册失败！");
            }
        }else if(sign.equals("2")){
            result = ResultUtil.error(2,"验证码过期");
        }else if(sign.equals("3")){
            result = ResultUtil.error(2,"验证码不正确");
        }else{
            result = ResultUtil.error(2,"验证失败");
        }

        return result;

    }

    /**
     * @Author: 王晨
     * @Description: 通过手机找user
     * @param: phone 手机号
     * @Date: 9:42 2017/9/21

    public Result findByPhone(String phone) throws  Exception{
        AUser user = userBiz.findByPhone(phone);
        Result result;
        if(user!=null){0
            result = ResultUtil.success(user);
        }else{
            result =  ResultUtil.error(2,"查询失败");
        }
        return result;
    }
    /**
     * @Author: 王晨
     * @Description: acessToken找user
     * @param: phone 手机号
     * @Date: 9:42 2017/9/21
     */
    @GetMapping("findByAcessToken")
    public Result findByAcessToken(String accessToken) throws  Exception{
        Result result;
        System.out.println("accessToken::"+accessToken);
        if(accessToken!=null){

        AUser user = tokenBiz.findByAcessToken(accessToken);
            System.out.println(user.toString());

        if(user!=null){
            user.setPassword("");
            result = ResultUtil.success(user);
        }else{
            result =  ResultUtil.error(2,"查询失败");
        }
        }else{
            result = ResultUtil.error(2,"accessToken不能为null");
        }
        return result;
    }
    /**
     * @Author: 王晨
     * @Description: 修改密码
     * @param: phone 手机号 code 验证码 password密码  accessToken 令牌
     * @Date: 9:42 2017/9/21
     */
    @GetMapping("updatePw")
    public Result updatePw(HttpSession session,String phone,String code,String password,String accessToken) throws  Exception{
        Result result;
        if(phone!=null){
        if(code!=null){
        if(password!=null){
        if(accessToken!=null){
        TAccesstoken acessToken = tokenBiz.findAcessToken(accessToken);
        if(acessToken!=null){

            AUser user = userBiz.findById(acessToken.getAid());

            if(phone.equals(user.getTel())){

        if(user!=null){

        String sign = SmsUtil.verificationCode(session, phone, code);
        if(sign.equals("1")){

            String pw = MD5.md5(password);

            if(!pw.equals(user.getPassword())){
                user.setPassword(pw);
                userBiz.update(user);
                result = ResultUtil.success(1,"密码修改成功");
            }else{
                result = ResultUtil.error(2,"与原密码相同");
            }

        }else if(sign.equals("2")){
            result = ResultUtil.error(2,"验证码过期");
        }else if(sign.equals("3")){
            result = ResultUtil.error(2,"验证码不正确");
        }else{
            result = ResultUtil.error(2,"验证失败");
        }

        }else{
            result = ResultUtil.error(2,"修改失败");
        }

            }else{
                result = ResultUtil.error(2,"与该账号手机号不一致");
            }

        }else{
            result = ResultUtil.error(3,"accessToken过期");
        }

        }else{
            result = ResultUtil.error(2,"accessToken不能为null");
        }

        }else{
            result = ResultUtil.error(2,"密码不能为null");
        }

        }else{
            result = ResultUtil.error(2,"验证码不能为null");
        }

        }else{
            result = ResultUtil.error(2,"手机号不能为null");
        }

        return result;
    }

    /**
     * @Author: 王晨
     * @Description:修改用户信息
     * @param:  user 用户对象，accessToken 令牌
     * @Date: 16:54 2017/9/23
     */
    @PostMapping("updateUser")
    public Result updateUser(@RequestBody AUser user,String accessToken) throws Exception{
        System.out.println("user:::"+user.toString());
        Result result;
        if(accessToken!=null){
            TAccesstoken acessToken = tokenBiz.findAcessToken(accessToken);
            if(acessToken!=null){
                AUser admin = userBiz.findById(acessToken.getAid());
                if(admin!=null){
                    admin = copyUser.copy(user,admin);
                    // ObjectCopyUtil.copy();
                    System.out.println("admin.getIoc();::"+admin.getIoc());
                    boolean flag = userBiz.update(admin);
                    if(flag){
                        result = ResultUtil.success(1,"修改成功",admin);
                    }else{
                        result = ResultUtil.error(2,"修改失败");
                    }

                }else{
                    result = ResultUtil.error(2,"修改失败");
                }

            }else{
                result = ResultUtil.error(3,"accessToken过期");
            }
        }else{
            result = ResultUtil.error(2,"accessToken不能为null");
        }
     return result;
    }
}
