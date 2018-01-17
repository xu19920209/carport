package com.tuyue.web.feedback.controller;

import com.tuyue.bean.Result;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.feedback.bean.IFeedBean;
import com.tuyue.web.feedback.biz.IIFeedbackBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/22.
 * @Modified By:
 */
@RestController
@RequestMapping("Feedback")
public class FeedbackController {
    @Autowired
    private IIFeedbackBiz iFeedbackBiz;
    /**
     * @Author: 王金海
     * @Description: 意见反馈
      * @param iFeedBean
     * @Date: 10:38 2017/9/22
     */
    @RequestMapping("add")
    public Result add(IFeedBean iFeedBean) throws Exception {
        boolean b = iFeedbackBiz.add(iFeedBean);
        if (b) {
            return ResultUtil.success();
        }
        return ResultUtil.error(2,"反馈失败");
    }
}
