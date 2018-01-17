package com.tuyue.web.publish.controller;

import com.tuyue.bean.Result;
import com.tuyue.pojo.BCarportOut;
import com.tuyue.pojo.TAccesstoken;
import com.tuyue.util.Page;
import com.tuyue.util.ResultUtil;
import com.tuyue.util.SmsUtil;
import com.tuyue.web.login.biz.ITAccesstokenBiz;
import com.tuyue.web.publish.biz.IBCarportOutBiz;
import com.tuyue.web.publish.biz.bean.BCarportOutImgBean;
import com.tuyue.web.publish.biz.bean.BCarportOutlistBean;
import com.tuyue.web.publish.biz.bean.ListBCarportOutByAidBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description: 车位出租
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
@RestController
@RequestMapping("BCarportOut")
public class BCarportOutController {

    @Autowired
    private IBCarportOutBiz bCarportOutBiz;

    @Autowired
    private ITAccesstokenBiz accessTokenBiz;

    /**
     * @Author: 王金海
     * @Description: 发布出租车位
     * @param bCarportOutImgBean 车位图片对象
     * @Date: 10:22 2017/9/20
     */
    @PostMapping("PublishHire")
    public Result PublishHire(@RequestBody BCarportOutImgBean bCarportOutImgBean, String code, HttpSession session) throws Exception {
        if (code==null) {
            return ResultUtil.error(2,"验证码不能为空");
        }
        String code1 = SmsUtil.verificationCode(session, bCarportOutImgBean.getPjone(), code);
        int i = Integer.parseInt(code1);
        if (i==1) {
            boolean b = bCarportOutBiz.PublishHire(bCarportOutImgBean);
            if (b){
                return ResultUtil.success("发布成功");
            }
            return ResultUtil.error(2,"发布失败");
        }
        if (i==2) {
            return ResultUtil.error(2,"验证码错误");
        }
        if (i==3) {
            return ResultUtil.error(2,"验证码过期");
        }
        return ResultUtil.error(2,"发布失败");
    }
    
    /**
     * @Author: 王金海
     * @Description:  出租转让list
     * @param carportType    车位类型（1露天2机械3地下）
     * @param enterpriseType 个人企业flag（1个人2企业）
     * @param province 省
     * @param city         市
     * @param region         区
     * @param rentalscope    租金范围
     * @param rentType       出租转让（1出租2转让）
     * @Date: 13:43 2017/9/20
     */
    @GetMapping("listBCarportOut")
    public Result listBCarportOut(@RequestParam(value = "province",required = true) String province,@RequestParam(value = "city",required = true)String city, @RequestParam(value = "currentPage",required = true) Integer currentPage,@RequestParam(value = "pageSize",required = true)  Integer pageSize, Integer carportType,@RequestParam(value = "rentalscope",required = false) String rentalscope, @RequestParam(value = "region",required = false)String region,@RequestParam(value = "enterpriseType",required = false) Integer enterpriseType,@RequestParam(value = "rentType",required = true) Integer rentType,String str,@RequestParam(value = "isSale",required = true)Integer isSale) throws Exception {

        Page<BCarportOutlistBean> bCarportOutPage = bCarportOutBiz.list(province,city,currentPage, pageSize, carportType, rentalscope, region, enterpriseType, rentType,str,isSale);
        return ResultUtil.success(bCarportOutPage);
    }

    /**
     * @Author: 王金海
     * @Description:  车位出租出售查看详情+谁看过我
     * @param bid 出租出售表ID
     * @Date: 17:54 2017/9/20
     */
    @GetMapping("getBCarportOut")
    public Result getBCarportOut(Integer bid,@RequestParam(value = "baid",required = true) Integer baid,@RequestParam(value = "baid",required = false) Integer aaid) throws Exception {
        if (bid==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        BCarportOutImgBean bCarportOutImgBean = bCarportOutBiz.getInfo(bid,baid,aaid);
        return ResultUtil.success(bCarportOutImgBean);
    }

    /**
     * @Author: 王晨
     * @Description: 内容中心--》信息管理 车位出租，车位转让列表
     * @param:aid 用户id ,rentType 出租转让 1出租 2装让,isPutaway 是否上架 1 是 0 否，currentPage需要获取的页码，pageSize 每页显示数
     * @Date: 9:29 2017/9/22
     */
    @GetMapping("listBCarportOutByAid")
    public Result listBCarportOutByAid(Integer currentPage, Integer pageSize,String accessToken,Integer rentType,Integer isPutaway) throws Exception{

        Result result;
        if(accessToken!=null){
            TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
            if(token!=null){
                Page<ListBCarportOutByAidBean> pages = null;
                try {
                    pages = bCarportOutBiz.listBCarportOutByAid(currentPage, pageSize, token.getAid(), rentType, isPutaway);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result = ResultUtil.success(pages);
            }else{
                result =  ResultUtil.error(3,"token已过期");
            }

        }else{
            result =  ResultUtil.error(2,"token不能为null");
        }

        return result;
    }
    /**
     * @Author: 王晨
     * @Description:通过车位出租ID查找车位出租对象
     * @param: 车位出租ID
     * @Date: 11:30 2017/9/23
     */
    @GetMapping("findBCarportOutById")
    public Result findBCarportOutById(Integer bid,String accessToken) throws Exception{
        Result result;
        if(bid!=null){
            if(accessToken!=null){
                TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
                if(token!=null){

                    BCarportOutImgBean bean = bCarportOutBiz.findByBid(bid);

                    if(bean!=null){
                        result = ResultUtil.success(bean);
                    }else{
                        result =  ResultUtil.error(2,"bid传错没查不到对象");
                    }
                }else{
                    result =  ResultUtil.error(3,"token已过期");
                }

            }else{
                result =  ResultUtil.error(2,"token不能为null");
            }

        }else{
            result =  ResultUtil.error(2,"bid不能为null");
        }
        return result;
    }

    /**
     * @Author: 王晨
     * @Description:修改车位出租对象
     * @param: BCarportOut 车位出租对象 ，accessToken 令牌
     * @Date: 11:30 2017/9/23
     */
    @PostMapping("updateBCarportOut")
    public Result updateBCarportOut(@RequestBody BCarportOutImgBean BCarportOutImgBean,String accessToken) throws Exception{
        Result result;
        out.println("accessToken:::"+accessToken);
        if(BCarportOutImgBean!=null){
            if(accessToken!=null){
                TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
                if(token!=null){
                    boolean flag = bCarportOutBiz.updateBCarportOutImgBean(BCarportOutImgBean);
                    if(flag){
                        result = ResultUtil.error(1,"修改成功");
                    }else{
                        result = ResultUtil.error(2,"修改失败");
                    }
                }else{
                    result =  ResultUtil.error(3,"token已过期");
                }

            }else{
                result =  ResultUtil.error(2,"token不能为null");
            }

        }else{
            result = ResultUtil.error(2,"bCarportOutImgBean不能为null");
        }
        return result;
    }
    /**
     * @Author: 王晨
     * @Description:修改上架下架状态
     * @param: bid 车位出租对象ID,accessToken 令牌
     * @Date: 11:30 2017/9/23
     */
    @GetMapping("updateIsPutaway")
    public Result updateIsPutaway(String accessToken,Integer bid) throws  Exception{
        Result result;
        if(bid!=null){
        if(accessToken!=null){
            TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
            if(token!=null){
                BCarportOut bCarportOut = bCarportOutBiz.findById(bid);
                out.println("bCarportOut.toString()::"+bCarportOut.toString());
                if(bCarportOut!=null){
                   if((bCarportOut.getIsPutaway())==2){
                       bCarportOut.setIsPutaway(1);
                   }else {
                       bCarportOut.setIsPutaway(2);
                   }
                    boolean flag = bCarportOutBiz.updateBCarportOut(bCarportOut);
                   if(flag){
                       result = ResultUtil.error(1,"修改成功") ;
                   }else{
                       result = ResultUtil.error(2,"修改失败") ;
                   }

                }else{
                    result = ResultUtil.error(2,"bid查不到BCarportOut对象") ;
                }

            }else{
                result =  ResultUtil.error(3,"token已过期");
            }

        }else{
            result =  ResultUtil.error(2,"token不能为null");
        }

        }else{
            result =  ResultUtil.error(2,"bid不能为null");
        }
        return result;
    }
    /**
     * @Author: 王晨
     * @Description:降价
     * @param: accesstoken 令牌 ，lowerMoney降价
     * @Date: 14:19 2017/9/23
     */
    @GetMapping("lowerMoney")
    public Result  lowerMoney(String accessToken, Double lowerMoney, Integer bid) throws Exception{
        Result result;
        if(lowerMoney!=null){
        if(bid!=null){
        if(accessToken!=null){
            TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
            if(token!=null){
                BCarportOut bCarportOut = bCarportOutBiz.findById(bid);
                if(bCarportOut!=null){
                    bCarportOut.setIsLowerMoney(1);
                    bCarportOut.setLowerMoney(lowerMoney);
                    boolean flag = bCarportOutBiz.updateBCarportOut(bCarportOut);
                    if(flag){
                        result = ResultUtil.error(1,"修改成功");
                    }else{
                        result = ResultUtil.error(2,"修改失败");
                    }
                }else{
                    result =  ResultUtil.error(2,"bid查不到对象");
                }

            }else{
                result =  ResultUtil.error(3,"token已过期");
            }

        }else{
            result =  ResultUtil.error(2,"token不能为null");
        }

        }else{
            result =  ResultUtil.error(2,"bid不能为null");
        }

        }else{
            result =  ResultUtil.error(2,"lowerMoney不能为null");
        }
        return result;
    }
    /**
     * @Author: 王晨
     * @Description:删除车位出租对象
     * @param: bid 车位出租ID
     * @Date: 17:20 2017/9/23
     */
    @GetMapping("deleteBcar")
    public Result deleteBcar(String accessToken,Integer bid) throws  Exception{
        Result result;
        if(bid!=null){
        if(accessToken!=null){
            TAccesstoken token = accessTokenBiz.findAcessToken(accessToken);
            if(token!=null){
                int num = bCarportOutBiz.delete(bid);
                switch (num){
                    case 1:
                        result =  ResultUtil.success(1,"删除成功");
                        break;
                    case 2:
                        result =  ResultUtil.error(2,"bid查不到对象");
                        break;
                    case 3:
                        result =  ResultUtil.error(2,"删除失败");
                        break;
                    default:
                        result =  ResultUtil.error(2,"删除失败");
                        break;
                }
            }else{
                result =  ResultUtil.error(3,"token已过期");
            }

        }else{
            result =  ResultUtil.error(2,"token不能为null");
        }
        }else{
            result =  ResultUtil.error(2,"bid不能为null");
        }
        return result;
    }
}
