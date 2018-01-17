package com.tuyue.web.area.areaController;

import com.tuyue.bean.Result;
import com.tuyue.pojo.ArsfcGeoProvince;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.area.biz.AreaBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/27.
 * @Modified By:
 */
@RestController
@RequestMapping("area")
public class AreaController {
    @Autowired
    private AreaBiz biz;

    /**
     * @Author: 王金海
     * @Description:市列表
     * @Date: 11:43 2017/9/27
     */
    @RequestMapping("cityList")
    public Result cityList() throws Exception {
        return biz.cityList();
    }

    /**
     * @Author: 王金海
     * @Description:根据市查询下面的区
     * @Date: 13:36 2017/9/27
     */
    @RequestMapping("quList")
    public Result quList(String name) throws Exception {
        out.println(name);
        if (name == null || name.equals("")) {
            return ResultUtil.error(2, "参数不能为空！");
        }
        return biz.quList(name);
    }

    @RequestMapping("getProvince")
    public Result getProvince(@RequestParam (value="parentAdcode", required =true) String parentAdcode) throws Exception {
        ArsfcGeoProvince findone = biz.findone(parentAdcode);
        return ResultUtil.success(findone);
    }
}
