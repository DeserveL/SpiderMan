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

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DeserveL
 * @date 2017/6/23 22:47
 * @since 1.0.0
 */
public class ZipUtil {

    /**
     * zip压缩文件
     *
     * @param dir
     * @param zippath
     */
    public static void zip(String dir, String zippath) {
        compressFilesZip(getFiles(dir), zippath, dir);
    }

    /**
     * 递归取到当前目录所有文件
     *
     * @param dir 需要遍历的目录
     * @return
     */
    public static List<String> getFiles(String dir) {
        List<String> lstFiles = new ArrayList<>();
        File file = new File(dir);
        File[] files = file.listFiles();
        //第一次的时候判断目录是否存在
        if (files == null) {
            return null;
        } else {
            for (File f : files) {
                //判断是否为目录
                if (f.isDirectory()) {
                    lstFiles.add(f.getAbsolutePath()); //添加目录
                    lstFiles.addAll(getFiles(f.getAbsolutePath())); //添加目录下的所有文件
                } else {
                    lstFiles.add(f.getAbsolutePath());
                }
            }
            return lstFiles;
        }
    }

    /**
     * 文件名处理
     *
     * @param dir
     * @param path
     * @return
     */
    public static String getFilePathName(String dir, String path) {
        String p = path.replace(dir + File.separator, "");
        p = p.replace("\\", "/");
        return p;
    }

    /**
     * 把文件压缩成zip格式
     *
     * @param files       需要压缩的文件
     * @param zipFilePath 压缩后的zip文件路径   ,如"D:/test/aa.zip";
     */
    public static void compressFilesZip(List<String> files, String zipFilePath, String dir) {
        if (files == null || files.size() == 0) {
            return;
        }
        ZipArchiveOutputStream zaos = null;
        try {
            File zipFile = new File(zipFilePath);
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            //将每个文件用ZipArchiveEntry封装
            //再用ZipArchiveOutputStream写到压缩文件中
            for (String strfile : files) {
                File file = new File(strfile);
                if (file != null) {
                    String name = getFilePathName(dir, strfile);
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, name);
                    zaos.putArchiveEntry(zipArchiveEntry);
                    if (file.isDirectory()) {
                        continue;
                    }
                    InputStream is = null;
                    try {
                        is = new BufferedInputStream(new FileInputStream(file));
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            //把缓冲区的字节写入到ZipArchiveEntry
                            zaos.write(buffer, 0, len);
                        }
                        zaos.closeArchiveEntry();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (is != null)
                            is.close();
                    }
                }
            }
            zaos.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (zaos != null) {
                    zaos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 把zip文件解压到指定的文件夹
     *
     * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir 解压后的文件存放路径, 如"D:/test/" ()
     */
    public static void unzip(String zipFilePath, String saveFileDir) {
        if (!saveFileDir.endsWith("\\") && !saveFileDir.endsWith("/")) {
            saveFileDir += File.separator;
        }
        File dir = new File(saveFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(zipFilePath);
        if (file.exists()) {
            InputStream is = null;
            ZipArchiveInputStream zais = null;
            try {
                is = new FileInputStream(file);
                zais = new ZipArchiveInputStream(is);
                ArchiveEntry archiveEntry = null;
                while ((archiveEntry = zais.getNextEntry()) != null) {
                    // 获取文件名
                    String entryFileName = archiveEntry.getName();
                    // 构造解压出来的文件存放路径
                    String entryFilePath = saveFileDir + entryFileName;
                    OutputStream os = null;
                    try {
                        // 把解压出来的文件写到指定路径
                        File entryFile = new File(entryFilePath);
                        if (entryFileName.endsWith("/")) {
                            entryFile.mkdirs();
                        } else {
                            os = new BufferedOutputStream(new FileOutputStream(
                                    entryFile));
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = zais.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                        }
                    } catch (IOException e) {
                        throw new IOException(e);
                    } finally {
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (zais != null) {
                        zais.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getFiles("D:\\testpic"));
        zip("D:\\testpic","D:\\testpic1\\1.zip");
    }
}
