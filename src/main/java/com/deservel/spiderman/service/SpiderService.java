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

import com.deservel.spiderman.common.advice.BusinessException;
import com.deservel.spiderman.common.utils.ZipUtil;
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

        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String uuid = UUID.randomUUID().toString();

        //生成图片保存主路径(主目录/年/月/唯一标识)
        String path = ConfigurerPropertiesHolder.getPropertyWithFormat("pic.uniquePath", year, month, uuid);
        //图片保存路径(主目录/年/月/唯一标识/pic/)
        String picPath = ConfigurerPropertiesHolder.getPropertyWithFormat("pic.picPath", year, month, uuid);
        //压缩图片名(年_月_唯一标识_allPic.zip)
        String zipName = ConfigurerPropertiesHolder.getProperty("pic.zip.Name");
        //图片压缩保存路径(主目录/年/月/唯一标识/allPic.zip)
        String zipPathAndName = path + zipName;
        //创建图片保存路径
        File file = new File(picPath);
        if (file.mkdirs()) {
            //TODO pull
            //文件压缩
            ZipUtil.zip(picPath, zipPathAndName);
            return ConfigurerPropertiesHolder.getPropertyWithFormat("pic.zip.mark", year, month, uuid);
        } else {
            throw new BusinessException("服务器路径创建失败");
        }
    }
}
