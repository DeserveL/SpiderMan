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

import com.deservel.spiderman.common.web.ConfigurerPropertiesHolder;
import org.junit.Test;

/**
 * @author DeserveL
 * @date 2017/6/25 0025 下午 17:17
 * @since 1.0.0
 */
public class ConfigurerPropertiesHolderTest extends AbstractSpringContextTest{
    @Test
    public void getProperties(){
        System.out.println(ConfigurerPropertiesHolder.getProperty("zip.filePath"));
        System.out.println(ConfigurerPropertiesHolder.getProperty("log4j.appender.console"));
    }
}
