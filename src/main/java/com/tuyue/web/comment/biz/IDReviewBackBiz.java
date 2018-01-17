package com.tuyue.web.comment.biz;

import com.tuyue.pojo.DReviewBack;
import com.tuyue.util.Page;
import com.tuyue.web.comment.bean.CommentBean;
import com.tuyue.web.comment.bean.Commentstra;

import java.sql.SQLException;

/**
 * @Author: 王金海
 * @Description: 车位出租转租评论
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
public interface IDReviewBackBiz {
    /**
     * @Author: 王金海
     * @Description:  增加评论
      * @param dReviewBack
     * @Date: 9:15 2017/9/21
     */
    boolean add(DReviewBack dReviewBack) throws Exception;

    /**
     * @Author: 王金海
     * @Description: 通过出租ID查找对应评论
      * @param bid
     * @Date: 11:04 2017/9/21
     */
    Page<DReviewBack> list(Integer currentPage,Integer pageSize,Integer bid,Integer reviewType) throws SQLException;


    /**
     * @Author: 王金海
     * @Description: 好评率和得分
      * @param bid
     * @Date: 17:01 2017/9/21
     */
    Commentstra getStar(Integer bid) throws Exception;

    /**
     * @Author: 王金海
     * @Description:  好评中评差评
      * @param bid
     * @Date: 17:20 2017/9/21
     */
    CommentBean getCommentcount(Integer bid);
}
