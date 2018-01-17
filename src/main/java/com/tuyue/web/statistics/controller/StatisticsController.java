package com.tuyue.web.statistics.controller;

import com.tuyue.bean.Result;
import com.tuyue.util.Page;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.statistics.bean.AuserBean;
import com.tuyue.web.statistics.biz.IGLookMeBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王金海
 * @Description: 统计
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@RestController
@RequestMapping("Statistics")
public class StatisticsController {

    @Autowired
    private IGLookMeBiz igLookMeBiz;

    /**
     * @Author: 王金海
     * @Description:  统计谁看过我
      * @param flag （1谁看过我2谁感兴趣3谁想买4谁买过5谁联系过我）
      * @param aid 用户ID
     * @Date: 20:31 2017/9/21
     */
    @GetMapping("list")
            public Result list(@RequestParam(value = "currentPage",required = true)Integer currentPage,@RequestParam(value = "pageSize",required = true) Integer pageSize,@RequestParam(value = "flag",required = true) Integer flag,@RequestParam(value = "aid",required = true) Integer aid) throws Exception {
        Page<AuserBean> list = igLookMeBiz.list(currentPage, pageSize, flag, aid);
        return ResultUtil.success(list);
    }
}
