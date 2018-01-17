package com.tuyue.web.comment.controller;

import com.tuyue.bean.Result;
import com.tuyue.pojo.DReviewBack;
import com.tuyue.util.Page;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.comment.bean.CommentBean;
import com.tuyue.web.comment.bean.Commentstra;
import com.tuyue.web.comment.biz.IDReviewBackBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: 王金海
 * @Description: 评论
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
@RestController
@RequestMapping("Comment")
public class CommentController {

    @Autowired
    private IDReviewBackBiz reviewBackBiz;
    /**
     * @Author: 王金海
     * @Description: 增加评论
      * @param dReviewBack
     * @Date: 9:22 2017/9/21
     */
    @PostMapping("add")
    public Result add(@RequestBody DReviewBack dReviewBack) throws Exception {
        if (dReviewBack.getAid()==null) {
            return ResultUtil.error(2,"用户ID不能为空");
        }
        if (dReviewBack.getBackType()==null) {
            return ResultUtil.error(2,"评论类型不能为空（backType(0评论1回复)）");
        }
        if(dReviewBack.getBackType()==1){
            if (dReviewBack.getBackId()==null) {
                return ResultUtil.error(2,"回复父ID不能为空");
            }
        }
        boolean b = reviewBackBiz.add(dReviewBack);
        if (b){
            return ResultUtil.success();
        }
        return ResultUtil.error(2,"增加失败");
    }

    /**
     * @Author: 王金海
     * @Description: 通过出租ID查找对应评论
     * @param bid
     * @Date: 11:04 2017/9/21
     */
    @GetMapping("list")
    public Result list(@RequestParam(value = "currentPage",required = true) Integer currentPage, @RequestParam(value = "pageSize",required = true)  Integer pageSize,@RequestParam(value = "bid",required = true) Integer bid,@RequestParam(value = "reviewType",required = false)Integer reviewType) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<DReviewBack> dReviewBackPage = reviewBackBiz.list(currentPage, pageSize, bid, reviewType);
        Commentstra commentstra = reviewBackBiz.getStar(bid);
        CommentBean commentBean = reviewBackBiz.getCommentcount(bid);
        map.put("commentstra",commentstra);
        map.put("dReviewBackPage",dReviewBackPage);
        map.put("commentBean",commentBean);
        return ResultUtil.success(map);
    }
}
