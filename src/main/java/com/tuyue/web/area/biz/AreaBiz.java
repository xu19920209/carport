package com.tuyue.web.area.biz;

import com.tuyue.bean.Result;
import com.tuyue.pojo.ArsfcGeoProvince;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/27.
 * @Modified By:
 */
public interface AreaBiz {
    /**
     * @Author: 王金海
     * @Description:市列表
     * @Date: 11:43 2017/9/27
     */
    public Result cityList() throws Exception;

    /**
     * @Author: 王金海
     * @Description:根据市查询下面的区
     * @Date: 13:36 2017/9/27
     */
    public Result quList(String adcode) throws Exception;


    public ArsfcGeoProvince findone(String parentAdcode) throws Exception;
}
