package com.tuyue.web.publish.biz;

import com.tuyue.pojo.BCarportOut;
import com.tuyue.util.Page;
import com.tuyue.web.publish.biz.bean.BCarportOutImgBean;
import com.tuyue.web.publish.biz.bean.BCarportOutlistBean;
import com.tuyue.web.publish.biz.bean.ListBCarportOutByAidBean;

import java.util.List;

/**
 * @Author: 王金海
 * @Description: 车位出租转租
 * @Date: Created by Administrator on 2017/9/20.
 * @Modified By:
 */
public interface IBCarportOutBiz {
    /**
     * @param bCarportOutImgBean  租售对象加图片
     * @Author: 王金海
     * @Description: 发布出租信息
     * @Date: 10:16 2017/9/20
     */
    boolean PublishHire(BCarportOutImgBean bCarportOutImgBean) throws Exception;

    /**
     * @param carportType    车位类型（1露天2机械3地下）
     * @param enterpriseType 个人企业flag（1个人2企业）
     * @param province 省
     * @param city         市
     * @param region         区
     * @param rentalscope    租金范围
     * @param rentType       出租转让（1出租2转让）
     * @Author: 王金海
     * @Description: 分页查询list车位出租转租
     * @Date: 10:42 2017/9/20
     */
    Page<BCarportOutlistBean> list(String province, String city, Integer currentPage, Integer pageSize, Integer carportType, String rentalscope, String region, Integer enterpriseType, Integer rentType,String str,Integer isSale) throws Exception;

    /**
     * @Author: 王金海
     * @Description:  车位出租出售
      * @param bid 出租出售表ID
     * @Date: 17:54 2017/9/20
     */
    BCarportOutImgBean getInfo(Integer bid,Integer baid,Integer aaid) throws Exception;
    
    /**
     * @Author: 王晨
     * @Description: 通过手机号查询车位出租对象
     * @param: mobile 手机号
     * @Date: 9:37 2017/9/21
     */
    public List<BCarportOut> findListByMoble(String mobile) throws  Exception;

    /**
     * @Author: 王晨
     * @Description:修改车位出租对象
     * @param: bCarportOut  车位出租对象
     * @Date: 9:50 2017/9/21
     */
    public boolean updateBCarportOut(BCarportOut bCarportOut) throws Exception;
    /**
     * @Author: 王晨
     * @Author: 王晨
     * @Description: 内容中心--》信息管理 车位出租，车位转让列表
     * @param:aid 用户id ,rentType 出租转让 1出租 2装让,isPutaway 是否上架 1 是 0 否，currentPage需要获取的页码，pageSize 每页显示数
     * @Date: 11:29 2017/9/23
     */
    public Page<ListBCarportOutByAidBean> listBCarportOutByAid(Integer currentPage, Integer pageSize, Integer aid, Integer rentType, Integer isPutaway) throws  Exception;
    /**
     * @Author: 王晨
     * @Description:通过车位出租ID查找车位出租对象
     * @param: 车位出租ID
     * @Date: 11:30 2017/9/23
     */
    public BCarportOut findById(int bid) throws Exception;
    /**
     * @Author: 王晨
     * @Description:删除车位出租对象
     * @param: bid 车位出租ID
     * @Date: 17:20 2017/9/23
     */
    public int delete(int bid) throws  Exception;

    /**
     * @Author: 王晨
     * @Description: 修改 车位出租对象及img图片
     * @param: bCarportOutImgBean
     * @Date: 9:26 2017/9/29
     */
    public boolean updateBCarportOutImgBean(BCarportOutImgBean bCarportOutImgBean) throws Exception;

    /**
     * @Author: 王晨
     * @Description: 通过BID 查询 BCarportOutImgBean 对象
     * @param:bid
     * @Date: 9:42 2017/9/29
     */
    public BCarportOutImgBean findByBid(int bid) throws Exception;
}
