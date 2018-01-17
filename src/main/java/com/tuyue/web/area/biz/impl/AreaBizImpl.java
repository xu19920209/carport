package com.tuyue.web.area.biz.impl;

import com.tuyue.bean.Result;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.ArsfcGeoCity;
import com.tuyue.pojo.ArsfcGeoDistrict;
import com.tuyue.pojo.ArsfcGeoProvince;
import com.tuyue.util.ChineseToSpell;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.ResultUtil;
import com.tuyue.web.area.bean.ArsfcGeoCityBean;
import com.tuyue.web.area.biz.AreaBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/27.
 * @Modified By:
 */
@Service
public class AreaBizImpl implements AreaBiz {
    @Autowired
    private IBaseDao<ArsfcGeoCity> arsfcGeoCityIBaseDao;
    @Autowired
    private IBaseDao<ArsfcGeoDistrict> arsfcGeoDistrictIBaseDao;
    @Autowired
    private IBaseDao<ArsfcGeoProvince> arsfcGeoProvinceIBaseDao;
    /**
     * @Author: 王金海
     * @Description:市列表
     * @Date: 11:43 2017/9/27
     */
    public Result cityList() throws Exception {
        List<ArsfcGeoCity> list = arsfcGeoCityIBaseDao.findList("from ArsfcGeoCity");
        ArrayList<ArsfcGeoCityBean> arsfcGeoCityBeans = new ArrayList<ArsfcGeoCityBean>();
        for (ArsfcGeoCity bean : list) {
            String s = bean.getName().substring(0,1);
            String s1 = ChineseToSpell.getAllFirstSpell(s);
            ArsfcGeoCityBean arsfcGeoCityBean = ObjectCopyUtil.copy(bean, ArsfcGeoCity.class, ArsfcGeoCityBean.class);
            arsfcGeoCityBean.setLetter(s1);
            arsfcGeoCityBeans.add(arsfcGeoCityBean);
        }
        //排序
        Collections.sort(arsfcGeoCityBeans, new Comparator<ArsfcGeoCity>() {
            Collator collator = Collator.getInstance(java.util.Locale.CHINA);
            public int compare(ArsfcGeoCity arg0, ArsfcGeoCity arg1) {
                String name0 = arg0.getName();
                String name1  = arg1.getName();
                if(collator.compare(name0,name1)>0)
                    return 1;
                else
                    return -1;
            }
        });

        return ResultUtil.success(arsfcGeoCityBeans);
    }
    /**
     * @Author: 王金海
     * @Description:根据市查询下面的区
     * @Date: 13:36 2017/9/27
     */
    public Result quList(String name) throws Exception{
            List<ArsfcGeoDistrict> list = arsfcGeoDistrictIBaseDao.findList(" from ArsfcGeoDistrict where parentAdcode='" +name + "'");
            return ResultUtil.success(list);
     }

    @Override
    public ArsfcGeoProvince findone(String parentAdcode) throws Exception {
        ArsfcGeoProvince arsfcGeoProvince = arsfcGeoProvinceIBaseDao.findOne("from ArsfcGeoProvince where adcode='" + parentAdcode + "'");
        return arsfcGeoProvince;
    }


}
