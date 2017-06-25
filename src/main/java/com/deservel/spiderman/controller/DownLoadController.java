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

import com.deservel.spiderman.common.utils.IOUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author DeserveL
 * @date 2017/6/24 0:28
 * @since 1.0.0
 */
@Controller
@RequestMapping("/downLoad")
public class DownLoadController {

    @RequestMapping("/zipDownLoad")
    public ResponseEntity<byte[]> zipDownLoad(String name) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = name;
        try {
            fileName = new String((fileName).getBytes("gbk"), "iso-8859-1"); //设置中文格式
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(new MediaType("application", "octet-stream"));
        byte[] bytes = IOUtil.readStreamBytes(new FileInputStream("D:\\testpic\\zip\\" + name));
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
