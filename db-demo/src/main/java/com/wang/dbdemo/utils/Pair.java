package com.wang.dbdemo.utils;

import lombok.Data;

@Data
public class Pair<T1, T2> {
    private T1 dbNum;
    private T2 tableNum;

    public Pair(T1 dbNum, T2 tableNum) {
        this.dbNum = dbNum;
        this.tableNum = tableNum;
    }

}
