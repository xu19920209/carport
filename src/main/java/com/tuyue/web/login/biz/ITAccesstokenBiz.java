package com.tuyue.web.login.biz;

import com.tuyue.pojo.AUser;
import com.tuyue.pojo.TAccesstoken;

/**
 * Created by Yui on 2017/9/22.
 */
public interface ITAccesstokenBiz {
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

    /**
     * @Author: 王晨
     * @Description: 通过accessToken找accessToken对象
     * @param: token 用户accessToken
     * @Date: 10:46 2017/9/21
     */
    public TAccesstoken findAcessToken(String token) throws  Exception;
}
