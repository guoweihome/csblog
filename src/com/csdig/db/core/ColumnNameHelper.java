/*
 *  Date: 2011-7-22 ����10:34:46
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db.core;

public class ColumnNameHelper {

    public static String castColumnToClass(String columnName) {
        if (null == columnName || "".equals(columnName))
            return null;
        int idx = columnName.indexOf('_');
        StringBuffer result = new StringBuffer();
        String temp = columnName;
        while (idx != -1) {
            result.append(temp.substring(0, idx));
            temp = temp.substring(idx + 1);
            idx = temp.indexOf('_');
            if (temp.length() > 0) {
                temp = upperFirstLeter(temp);
            }
        }
        result.append(temp);
        return result.toString();
    }

    public static String upperFirstLeter(String str) {
        if (str.length() > 0) {
            String firstLeter = str.substring(0, 1);
            return str.replaceFirst(firstLeter, firstLeter.toUpperCase());
        }
        return str;
    }

    /**
     * Convert a name in camelCase to an underscored name in lower case.<br>
     * Any upper case letters are converted to lower case with a preceding underscore.
     * 
     * @param name
     *            the string containing original name
     * @return the converted name
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

}
