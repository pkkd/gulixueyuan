package com.atguigu.gulixueyuan.aliyun.config;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author helen
 * @since 2018/11/20
 */
public class AliyunOSSUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static String endpoint = ConstantProperties.END_POINT;
    private static String accessKeyId = ConstantProperties.ACCESS_KEY_ID;
    private static String accessKeySecret = ConstantProperties.ACCESS_KEY_SECRET;
    private static String bucketName = ConstantProperties.BUCKET_NAME;
    private static String fileHost = ConstantProperties.FILE_HOST;

    public static String upload(File file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());

        String dateStr = format.format(new Date());

        if (file == null) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());

            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));

            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            }

        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getMessage());
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 删除Object
     *
     * @param fileKey
     * @return
     */
    public static String deleteBlog(String fileKey) {
        logger.info("=========>OSS文件删除开始");
        try {
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            if (!ossClient.doesBucketExist(bucketName)) {
                logger.info("==============>您的Bucket不存在");
                return "您的Bucket不存在";
            } else {
                logger.info("==============>开始删除Object");
                ossClient.deleteObject(bucketName, fileKey);
                logger.info("==============>Object删除成功：" + fileKey);
                return "==============>Object删除成功：" + fileKey;
            }
        } catch (Exception ex) {
            logger.info("删除Object失败", ex);
            return "删除Object失败";
        }
    }

    /**
     * 查询文件名列表
     *
     * @param bucketName
     * @return
     */
    public static List<String> getObjectList(String bucketName) {
        List<String> listRe = new ArrayList<>();
        try {
            logger.info("===========>查询文件名列表");
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
            //列出blog目录下今天所有文件
            listObjectsRequest.setPrefix("blog/" + format.format(new Date()) + "/");
            ObjectListing list = ossClient.listObjects(listObjectsRequest);
            for (OSSObjectSummary objectSummary : list.getObjectSummaries()) {
                listRe.add(objectSummary.getKey());
            }
            return listRe;
        } catch (Exception ex) {
            logger.info("==========>查询列表失败", ex);
            return new ArrayList<>();
        }
    }

}
