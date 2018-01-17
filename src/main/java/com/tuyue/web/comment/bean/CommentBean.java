package com.tuyue.web.comment.bean;

/**
 * @Author: 王金海
 * @Description: 好评中评差评
 * @Date: Created by Administrator on 2017/9/21.
 * @Modified By:
 */
public class CommentBean {
    private long goodCount;
    private long middleCount;
    private long badCount;

    public long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(long goodCount) {
        this.goodCount = goodCount;
    }

    public long getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(long middleCount) {
        this.middleCount = middleCount;
    }

    public long getBadCount() {
        return badCount;
    }

    public void setBadCount(long badCount) {
        this.badCount = badCount;
    }
}
