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

/**
 * @author DeserveL
 * @date 2017/6/23 22:14
 * @since 1.0.0
 */
public class DownLoadPic {
    private static final String saveImgPath = "D://testpic";

    public void getDoc(String purl) throws IOException {
        //以网易为例子
        Document doc = Jsoup.connect(purl).get();
        //获取后缀为png和jpg的图片的元素集合
        Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        //遍历元素
        for (Element e : pngs) {
            String src = e.attr("src");//获取img中的src路径
            //获取后缀名
            String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
            //连接url
            URL url = new URL(src);
            URLConnection uri = url.openConnection();
            //获取数据流
            InputStream is = uri.getInputStream();
            //写入数据流
            String  name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            OutputStream os = new FileOutputStream(new File(saveImgPath, name));
            byte[] buf = new byte[1024];
            int l = 0;
            while ((l = is.read(buf)) != -1) {
                os.write(buf, 0, l);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new DownLoadPic().getDoc("https://tieba.baidu.com/p/5156377903");
    }
}
