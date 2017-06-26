/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deservel.spiderman.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DeserveL
 * @date 2017/6/26 14:57
 * @since 1.0.0
 */
public class SpiderUtil {

    /**
     * 抓取指定网址图片
     *
     * @param url  网址
     * @param path 保存路径
     * @return
     */
    public static boolean spiderImg(String url, String path) throws IOException {
        Set<String> imgUrl = getImgUrl(url);
        for (String src : imgUrl) {
            downLoadImg(src, path);
        }
        return false;
    }

    /**
     * 获取指定Url的所有图片地址
     *
     * @param url
     * @return
     */
    public static Set<String> getImgUrl(String url) throws IOException {
        Set<String> set = new HashSet<>();
        Document doc = Jsoup.connect(url).get();
        Elements pngs = doc.select("img[src~=(?i)\\.(gif|png|jpe?g)]");
        for (Element e : pngs) {
            String src = e.attr("src");//获取img中的src路径
            set.add(src);
        }
        return set;
    }

    /**
     * 保存单个图片
     *
     * @param src  图片网址
     * @param path 保存路径
     * @return
     * @throws IOException
     */
    public static boolean downLoadImg(String src, String path) {
        try {
            //获取后缀名
            String imageType = src.substring(src.lastIndexOf(".") + 1, src.length());
            //连接url
            URL url = new URL(src);
            URLConnection uri = url.openConnection();
            //获取数据流
            InputStream is = uri.getInputStream();
            //写入数据流
            String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            OutputStream os = new FileOutputStream(new File(path, name + "." + imageType));
            byte[] buf = new byte[1024];
            int l;
            while ((l = is.read(buf)) != -1) {
                os.write(buf, 0, l);
            }
        } catch (Exception e) {
            //单个图片失败不做处理
        }
        return false;
    }
}
