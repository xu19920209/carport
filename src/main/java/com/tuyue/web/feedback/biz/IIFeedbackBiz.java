package com.tuyue.web.feedback.biz;

import com.tuyue.web.feedback.bean.IFeedBean;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/22.
 * @Modified By:
 */
public interface IIFeedbackBiz {
    /**
     * @Author: 王金海
     * @Description: 意见反馈
      * @param iFeedBean
     * @Date: 10:09 2017/9/22
     */
    boolean add(IFeedBean iFeedBean) throws Exception;
}
