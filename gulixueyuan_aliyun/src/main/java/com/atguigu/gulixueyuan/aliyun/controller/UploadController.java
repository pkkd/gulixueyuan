package com.atguigu.gulixueyuan.aliyun.controller;

import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.aliyun.config.AliyunOSSUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;


@CrossOrigin
@RestController
@RequestMapping("/admin/aliyun/upload")
public class UploadController {

    @PostMapping("file")
    public R uploadFile(@RequestParam("avatar") MultipartFile avatar) throws Exception {


        String uploadurl = null;
        if(avatar != null){
            String filename = avatar.getOriginalFilename();
            if(!"".equals(filename.trim())){
                File newFile = new File(filename);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(avatar.getBytes());
                os.close();
                avatar.transferTo(newFile);

                //上传到oss
                uploadurl = AliyunOSSUtil.upload(newFile);
                return R.ok().message("文件上传成功").data(uploadurl);
            }
        }

        return R.error().message("请选择文件");

    }
}
