package com.tuyue.web.login.biz;

import com.tuyue.bean.Result;
import com.tuyue.pojo.AUser;
import com.tuyue.pojo.TAccesstoken;

/**
 * Created by 王晨 on 2017/9/20.
 */
public interface IAUserBiz {
    /**
     * 通过账号，密码登录
     * @param username
     * @param password
     * @return AUser
     */
    public Result loginByPassword(String username, String password) throws Exception;

    /**
     * 通过手机号，及验证码登录
     * @param phone
     * @param code
     * @return AUser

    public Result loginByMobileCode(String phone , String code);
     */
    /**
     * 验证手机号是否存在
     * @param phone
     * @return Result 1:存在  2:不存在
     */
    public Result mobileIsExist(String phone) throws Exception;
    /**
     * 验证用户名是否存在
     * @param userName
     * @return Result 1:存在  2:不存在
     */
    public Result userNameIsExist(String userName) throws Exception;

    /**
     * 通过手机号找用户
     * @param phone
     * @return AUSER
     */
    public AUser findByPhone(String phone) throws Exception;
    /**
     * 修改密码
     * @param user
     * @return boolean
     */
    public boolean update(AUser user) throws  Exception;

    /**
     * 通过ID找对象
     * @param id
     * @return Auser
     */
    public AUser findById(Integer id) throws  Exception;

    /**
     * 注册用户
     * @param user
     * @return
     */
    public int addUser(AUser user);

    /**
     * @Author: 王晨
     * @Description:生成一条accessToken 
     * @param: aid 用户id
     * @Date: 10:36 2017/9/21
     */
    public String addAcessToken(Integer aid);
    /**
     * @Author: 王晨
     * @Description: 通过accessTokenz找user
     * @param: accessToken 令牌号
     * @Date: 10:46 2017/9/21
     */
    public AUser findByAcessToken(String accessToken) throws  Exception;
    /**
     * @Author: 王晨
     * @Description: 通过aid找accessToken
     * @param: aid 用户id
     * @Date: 10:46 2017/9/21
     */
    public TAccesstoken findAcessToken(Integer aid) throws  Exception;
    /**
     * @Author: 王晨
     * @Description: 修改 accessToken
     * @param: accessToken 令牌对象
     * @Date: 10:46 2017/9/21
     */
    public boolean updateAcessToken(TAccesstoken accesstoken) throws  Exception;
}
