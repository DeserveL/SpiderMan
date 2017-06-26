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
package com.deservel.spiderman.controller;

import com.deservel.spiderman.common.advice.BusinessException;
import com.deservel.spiderman.common.utils.IOUtil;
import com.deservel.spiderman.common.web.ConfigurerPropertiesHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author DeserveL
 * @date 2017/6/24 0:28
 * @since 1.0.0
 */
@Controller
@RequestMapping("/downLoad")
public class DownLoadController {

    @RequestMapping("/zipDownLoad")
    public ResponseEntity<byte[]> zipDownLoad(String mark) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        String[] paths = mark.split("_");
        if(paths.length < 3){
            throw new BusinessException("参数错误");
        }
        String fileName = ConfigurerPropertiesHolder.getProperty("pic.zip.Name"); //zip文件名
        String path = ConfigurerPropertiesHolder.getProperty("pic.filePath") + paths[0] + "/" + paths[1] + "/" + paths[2] + "/" + fileName; //zip文件加路径
        //返回消息设置
        fileName = new String((fileName).getBytes("gbk"), "iso-8859-1"); //设置中文格式
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(new MediaType("application", "octet-stream"));
        //读取Zip文件
        byte[] bytes = IOUtil.readStreamBytes(new FileInputStream(path));
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
