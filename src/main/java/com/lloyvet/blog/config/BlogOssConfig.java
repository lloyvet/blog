package com.lloyvet.blog.config;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zihao Shen
 */
public class BlogOssConfig {



    private static final String accessKey = "KWaOcuBX_pnFYaSD1VV9-3DF7r2_E9VtI1yg5Epo";

    private static final String secretKey = "uiMGEWyeC4Bro6QiFto1n9djtRWGJCl65_pEolrW";

    private static final String bucketName = "imagelloyvet";

    // 密钥
    private static final String DOMAIN = "http://prom5oysi.bkt.clouddn.com";

    /**
     * 将图片上传到七牛云
     */
    public static String uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = DOMAIN + "/" + putRet.key;
                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
