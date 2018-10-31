package com.pinyougou.shop.controller;

import entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.FastDFSClient;

/**
 * @author songsenior
 * @date 2018/10/30 18:57
 * @Description:
 */
@RestController
public class UploadController {
    /**
     * 从配置文件中获取url
     */
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //得到拓展名
        String extNam = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            //创建一个fastdfs的client
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
            //进行图片上传处理
            String s = client.uploadFile(file.getBytes(), extNam);
            //得到完整地址返回
            String url = FILE_SERVER_URL + s;
            return new Result(true, url);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "滚鸡巴蛋");
        }

    }
}
