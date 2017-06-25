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
package test;

import com.deservel.spiderman.controller.DemoController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author DeserveL
 * @date 2017/5/31 16:19
 * @since 1.0.0
 */
public class DemoControllerTest extends AbstractSpringContextTest {

    @Autowired
    DemoController demoController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }

    @Test
    public void getName() throws Exception {
        ResultActions perform = this.mockMvc.perform(MockMvcRequestBuilders.post("/demo/getName").accept((MediaType.APPLICATION_JSON)).param("name", "李响").param("age", "18"));
        MvcResult mvcResult = perform.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + contentAsString);
    }
}
