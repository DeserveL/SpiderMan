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
package com.deservel.spiderman.service;

import com.deservel.spiderman.common.web.ConfigurerPropertiesHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author DeserveL
 * @date 2017/6/24 0024 下午 17:27
 * @since 1.0.0
 */
@Service
public class SpiderService {

    public String getPic(String url) {
        //生成图片保存路径(主目录/年/月/唯一标识)
        StringBuffer path = new StringBuffer(ConfigurerPropertiesHolder.getProperty("pic.filePath"));
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        path.append(calendar.get(Calendar.YEAR) + "/");
        int month = calendar.get(Calendar.MONTH);
        path.append(calendar.get(Calendar.MONTH) + "/");
        String uuid = UUID.randomUUID().toString();
        path.append(UUID.randomUUID().toString() + "/");
        //图片保存路径(主目录/年/月/唯一标识/pic/)
        String picPath = path.toString() + "pic/";
        File file = new File(picPath);
        System.out.println(file.mkdirs());
        //压缩图片名(年_月_唯一标识_allPic.zip)
        String zipName = year + "_" + month + "_" + uuid + "_allPic.zip";
        System.out.println(zipName);
        //图片保存路径(主目录/年/月/唯一标识/allPic.zip)
        String zipPathAndName = path.toString() + zipName;
        System.out.println(zipPathAndName);
        //String zipName = "allPic.zip";
        return zipName;
    }

    public static void main(String[] args) {
        new SpiderService().getPic("123");
    }
}
