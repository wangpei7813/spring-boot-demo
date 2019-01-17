package com.wang.dbdemo.utils;

public class SelectorUtil {

    private static final int writeDataBaseSize = 2;

    private static final int writeTableSize = 2;

    public static Pair<Integer, Integer> getDataBaseAndTableNumber(String uuid) {
        int hashcode = Math.abs(uuid.hashCode());
        System.err.println("hashcode: " + hashcode);
        int selectDataBaseNumber = (hashcode / writeTableSize) % writeDataBaseSize + 1;
        int selectTableNumber = hashcode % writeTableSize;
        System.err.println("----------- SelectorUtil: selectDataBaseNumber: " + selectDataBaseNumber + " ----------------");
        System.err.println("----------- SelectorUtil: selectTableNumber: " + selectTableNumber + " ----------------");
        return new Pair<>(selectDataBaseNumber, selectTableNumber);
    }

    public static void main(String[] args) {
        String uuid = KeyUtil.generatorUUID();
        int code = Math.abs(uuid.hashCode());
        System.err.println("code: " + code);
        int selectDataBaseNumber = (code / 5) % 4 + 1;
        int selectTableNumber = code % 5;
        System.err.println("selectDataBaseNumber: " + selectDataBaseNumber);
        System.err.println("selectTableNumber: " + selectTableNumber);
    }

}
