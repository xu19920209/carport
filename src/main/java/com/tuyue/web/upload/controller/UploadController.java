package com.tuyue.web.upload.controller;

import com.tuyue.bean.Result;
import com.tuyue.util.Const;
import com.tuyue.util.ResultUtil;
import com.tuyue.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * 王金海
 * 途悦信息
 * Created by dell on 2017/9/20.
 */
@Controller
@ResponseBody
@RequestMapping("upload")
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);
    @PostMapping("fileUpload")
    public Result fileUpload(@RequestParam(value = "file" , required = false) MultipartFile file){
        logger.error("上传文件");
        if(file==null){
            return ResultUtil.error(2,"文件不能为空！");
        }
        if (!file.isEmpty()) {
            String path = Tools.readTxtFile(Const.UPIMGPATH);
            long l = System.currentTimeMillis();
            String name=String.valueOf(l);// 文件名称
            String filename = file.getOriginalFilename();
            String[] split = filename.split("\\.");
            name+= Tools.getRandomNum()+"."+split[split.length-1];
            try {
                File f = new File(path, name);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(f);
               Map map =new HashMap();
                map.put("path","/img/"+name);
                return ResultUtil.success(map);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(2,"添加失败！");
            }
        }else{
            return ResultUtil.error(2,"请输入录音！");
        }
    }
}
