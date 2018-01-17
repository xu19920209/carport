package com.tuyue.web.feedback.bean;

import com.tuyue.pojo.CCarportImg;
import com.tuyue.pojo.IFeedback;

import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/22.
 * @Modified By:
 */
public class IFeedBean extends IFeedback {
    private List<CCarportImg> cCarportImgs;

    public List<CCarportImg> getcCarportImgs() {
        return cCarportImgs;
    }

    public void setcCarportImgs(List<CCarportImg> cCarportImgs) {
        this.cCarportImgs = cCarportImgs;
    }
}
