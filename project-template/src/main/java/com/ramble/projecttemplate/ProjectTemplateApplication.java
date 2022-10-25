package com.ramble.projecttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;


@SpringBootApplication
public class ProjectTemplateApplication {

    public static void main(String[] args) {
//		String html = HttpUtils.doGetHtml("https://www.bing.com/?mkt=zh-CN");
//		Document document = Jsoup.parse(html);
//		Elements e = document.select("#bgLink");
//		System.out.println(e.attr("href"));
//		HttpUtils.doGetImage("https://www.bing.com" + e.attr("href"));


        // 方法2 抓取接口数据 https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&nc=1603114218081&pid=hp&mkt=zh-CN
        String json = HttpUtils.doGetHtml("https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&nc=1603114218081&pid=hp&mkt=zh-CN");
        // 这里解析JSON我用的Jackson，大家也可以使用别的如GSON等
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            String url = jsonNode.get("images").get(0).get("url").asText();
            System.out.println(url);
//			HttpUtils.doGetImage("http://www.bing.com" + url);
            String fullUrl = "http://www.bing.com" + url;
            HttpUtils.downloadImg(fullUrl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//		SpringApplication.run(ProjectTemplateApplication.class, args);
    }

}
