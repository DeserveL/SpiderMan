/*
 * Copyright 2002-2016 the original author or authors.
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

package com.deservel.spiderman;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author DeserveL
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class) //此处调用Spring单元测试类
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"}) //加载Spring配置文件
public abstract class AbstractSpringContextTest {
}
