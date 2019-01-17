package com.wang.dbdemo.config.database;

import cn.hutool.core.util.StrUtil;

/**
 * @author wp
 * @date 2019/1/17 15:11
 */
public class DBContextHolder {

    public enum DBType {
        ORDER1_MASTER("order1-master"),
        ORDER2_MASTER("order2-master"),
        ORDER1_SLAVE("order1-slave"),
        ORDER2_SLAVE("order2-slave");
        private String code;

        DBType(String code){
            this.code = code;
        }

        public String getCode(){
            return code;
        }
    }

    // 保存数据源
    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> contextTableHolder = new ThreadLocal<>();

    public static void setDataBaseType(DBType dBType) {
        if(dBType == null) throw new NullPointerException();
        contextHolder.set(dBType);
    }

    public static void setTableName(String tableName) {
        if (StrUtil.isBlank(tableName)) {
            throw new NullPointerException();
        }
        contextTableHolder.set(tableName);
    }

    public static String getTableName() {
        String tableName = contextTableHolder.get();
        if (StrUtil.isBlank(tableName)) {
            throw new NullPointerException("表明不能为空");
        }
        return tableName;
    }


    public static DBType getDataBaseType(){
        return contextHolder.get() == null ? DBType.ORDER1_MASTER : contextHolder.get();
    }

    public static void clearDataBaseType(){
        contextHolder.remove();
    }

    public static void clearTableName(){
        contextTableHolder.remove();
    }
}
