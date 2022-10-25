package com.ramble.projecttemplate;

import cn.hutool.http.HttpUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project     project-template
 * Package     com.ramble.projecttemplate
 * Class       HttpUtils
 * Date        2022/10/21 16:57
 * Author      MingliangChen
 * Email       cnaylor@163.com
 * Description
 */

public class HttpUtils {
    // 配置HttpClients连接池管理器，可以自动释放连接池资源
    static PoolingHttpClientConnectionManager manager;

    static {
        // 为了方便我直接丢到静态代码块中初始化
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(100);
        manager.setDefaultMaxPerRoute(10);
    }


    public static String doGetHtml(String url) {
        // 创建httpclient对象用于发起请求
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
        // 创建get请求，指定uri
        HttpGet httpGet = new HttpGet(url);
        // 设置连接的一些参数，可以不设置
        httpGet.setConfig(getConfig());
        // 设置user-agent来欺骗网站是浏览器访问
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 如果返回状态码为200代表成功，则将响应数据返回
                return EntityUtils.toString(response.getEntity(), "utf8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    // 这里只需要释放response，httpclient由连接池管理器释放
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 下载图片的方法基本与get请求类似
     * 主要区别在我们不返回响应字符串，而是将响应数据直接通过字节流写入文件
     */
    public static void doGetImage(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 这里格式化下时间作为文件名
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S");
                String dateStr = simpleDateFormat.format(new Date());
                // 创建字节缓冲输出流,大家要先创建F:/img/bing文件夹，我这里没有进行判断
//                BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("F:\\img\\bing\\" + dateStr + ".png"));
                BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("F:\\img\\bing\\" + "1.png"));
                // 响应数据直接写入输出流中
                response.getEntity().writeTo(bs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(2000)
                .setConnectTimeout(3000)
                .setSocketTimeout(10 * 1000)
                .build();
        return config;
    }


    public static void downloadImg(String url) throws FileNotFoundException {
        OutputStream out = new FileOutputStream("1.png");
        HttpUtil.download(url, out, false);
    }

}