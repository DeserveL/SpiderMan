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

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DeserveL
 * @date 2017/6/24 0:37
 * @since 1.0.0
 */
public class IOUtil {
    public static byte[] readStreamBytes(InputStream inputStream) throws IOException {
        byte[] cache = new byte[2048];
        int len;
        byte[] bytes = new byte[0];
        while ((len = inputStream.read(cache)) > 0) {
            byte[] temp = bytes;
            bytes = new byte[bytes.length + len];
            System.arraycopy(temp, 0, bytes, 0, temp.length);
            System.arraycopy(cache, 0, bytes, temp.length, len);
        }
        if (bytes.length == 0) {
            return new byte[0];
        }
        return bytes;
    }
}
