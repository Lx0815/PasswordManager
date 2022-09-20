package com.d.passwordmanager.command.csv;

import org.apache.commons.text.WordUtils;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author: Ding
 * @date: 2022/9/20 13:14
 * @description:
 * @modify:
 */


public class CsvUtils {

    private CsvUtils() {}

    /**
     * 从 csv 文件中读取数据并转换成 指定的类
     *
     * @param clz 与 csv 文件中对应的实体类
     * @param fieldMapper 字段映射器
     * @param filePath 文件路径
     * @return 返回读取到的所有数据
     * @param <T> 实体类
     * @throws FileNotFoundException
     */
    public static <T> List<T> readAll(Class<T> clz, Map<String, String> fieldMapper, Path filePath) throws FileNotFoundException {
        if (Files.isDirectory(filePath)) {
            throw new FileNotFoundException("请选择文件而不是目录");
        }
        if (! filePath.toString().endsWith(".csv")) {
            throw new FileNotFoundException("文件格式错误");
        }

        File file = filePath.toFile();
        InputStream inputStream = new FileInputStream(file);

        List<T> tList = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String headers = br.readLine();
            String row;
            while (! ObjectUtils.isEmpty(row = br.readLine())) {

                T mapper = mapper(clz, fieldMapper, headers, row);
                tList.add(mapper);
            }

            return tList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理 pojo 字段到 CSV 列名称的映射，并赋值
     *
     * @param clz 实体类
     * @param heads 读取到的列名称，应由 分号 间隔
     * @param row 读取到的一行数据
     * @return 返回改行数据对应的对象
     * @param <T> 实体类
     */
    public static <T> T mapper(Class<T> clz, Map<String, String> fieldMapper, String heads, String row) {
        String[] dataArr = row.split(",");
        String[] headerArr = heads.split(",");

        try {
            T obj = clz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < headerArr.length; i++) {

                String fieldName = fieldMapper.get(headerArr[i]);
                if (ObjectUtils.isEmpty(fieldName)) continue;
                Method setXxx = clz.getMethod("set" + WordUtils.capitalize(fieldName), String.class);
                setXxx.invoke(obj, dataArr[i]);
            }

            return obj;

        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {

            throw new RuntimeException(e);
        }
    }
}
