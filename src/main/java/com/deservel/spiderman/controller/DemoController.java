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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DeserveL
 * @date 2017/5/31 15:29
 * @since 1.0.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @ResponseBody
    @RequestMapping("/getName")
    public Map<String, Object> getName(String name) {
        Map<String, Object> rs = new HashMap<>();
        rs.put("name", name);
        rs.put("age", "18");
        return rs;
    }
}
