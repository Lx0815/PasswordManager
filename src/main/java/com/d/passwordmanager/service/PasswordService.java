package com.d.passwordmanager.service;

import com.d.passwordmanager.pojo.PasswordRecord;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author: Ding
 * @date: 2022/8/26 10:26
 * @description: 处理密码相关的需求
 * @modify:
 */


public interface PasswordService {

    /**
     * 查询所有密码记录
     *
     */
    List<PasswordRecord> selectAll();

    /**
     * 新增一条密码记录
     *
     * @param passwordRecord 要新增的密码
     * @return 返回是否新增成功
     */
    boolean insertOne(PasswordRecord passwordRecord);

    /**
     * 更新密码相关信息，包括密码
     *
     * @param passwordRecord 新的密码对象
     * @return 返回是否更新成功
     */
    boolean updateByPasswordRecord(PasswordRecord passwordRecord);

    /**
     * 删除 List 中密码
     *
     * @param deleteList 要删除的密码集合
     * @return 返回是否删除成功
     */
    boolean deleteByList(List<PasswordRecord> deleteList);

    /**
     * 通过关键词查询密码
     *
     * @param keyword 关键词
     * @return
     */
    List<PasswordRecord> selectByKeyword(String keyword);

    /**
     * 从 csv 文件中导入密码
     *
     * @param file csv 文件
     * @param mapperMap {@link PasswordRecord} 与 csv 文件的映射关系
     * @return 返回是否导入成功
     */
    boolean importByCsv(File file, Map<String, String> mapperMap);

    /**
     * 将密码保存到 csv 文件
     *
     * @param file 要保存的文件
     * @param passwordService 本类被 Spring 增强后的对象
     * @return 返回是否导出成功
     */
    boolean savaToFile(File file, PasswordService passwordService);
}
