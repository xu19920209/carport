package com.tuyue.web.contact.controller;

import com.tuyue.bean.Result;
import com.tuyue.pojo.FRelationMe;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.contact.biz.IFRelationMeBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王金海
 * @Description: 请联系我
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@RestController
@RequestMapping("Contact")
public class ContactController {
    @Autowired
    private IFRelationMeBiz fRelationMeBiz;

    /**
     * @Author: 王金海
     * @Description: 请联系我 
      * @param fRelationMe 
     * @Date: 15:11 2017/9/21
     */
    @PostMapping("ContactMe")
    public Result ContactMe(@RequestBody FRelationMe fRelationMe){
        if (fRelationMe.getAid()==null) {
            return ResultUtil.error(2,"用户ID不能为空");
        }
        if (fRelationMe.getBid()==null) {
            return ResultUtil.error(2,"发布ID不能为空");
        }
        boolean b = fRelationMeBiz.add(fRelationMe);
        if (b){
            return ResultUtil.success();
        }
        return ResultUtil.error(2,"联系失败");
    }



}
