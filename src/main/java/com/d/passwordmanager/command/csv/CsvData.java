package com.d.passwordmanager.command.csv;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/9/20 13:19
 * @description:
 * @modify:
 */


public class CsvData<T> {

    private String[] heads;

    private List<T> dataList;

    public CsvData() {
    }

    public String[] getHeads() {
        return heads;
    }

    public void setHeads(String[] heads) {
        this.heads = heads;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
