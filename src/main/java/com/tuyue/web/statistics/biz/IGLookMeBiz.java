package com.tuyue.web.statistics.biz;

import com.tuyue.util.Page;
import com.tuyue.web.statistics.bean.AuserBean;

/**
 * @Author: 王金海
 * @Description: 统计
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
public interface IGLookMeBiz {
    /**
     * @Author: 王金海
     * @Description:  统计谁看过我
     * @param flag （1谁看过我2谁感兴趣3谁想买4谁买过5谁联系过我）
     * @param aid 用户ID
     * @Date: 20:31 2017/9/21
     */
    Page<AuserBean> list(Integer currentPage, Integer pageSize,Integer flag,Integer aid) throws Exception;
}
