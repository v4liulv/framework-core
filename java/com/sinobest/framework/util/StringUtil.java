package com.sinobest.framework.util;

/**
 * Created by liulv on 2017/6/29.
 * <p>
 * StringUtil 处理
 */
public class StringUtil {
    /**
     * 去掉最后一位字符
     *
     * @param str String
     * @return String 返回去掉最后一位的字符串
     */
    public static String getConstant(String str) {
        String result = "";

        if (str != null && !"".equals(str)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    /**
     * 去掉字符串中非数字的字符
     *
     * @param str String
     * @return String
     */
    public static String removeStringNoNumber(String str) {
        if (str.split("\\.").length > 0) {
            str = str.split("\\.")[0];
        }

        str = str.replaceAll("[^\\d]", "");
        return str;
    }

    /**
     * 去掉字符串中数字的字符
     *
     * @param str String
     * @return String
     */
    public static String removeStringNumber(String str) {
        if (str.split(".").length > 0) {
            str = str.split(".")[0];
        }
        str = str.replaceAll("[\\d]", "");
        return str;
    }

    /**
     * 删除数组的原始
     *
     * @param arr   数组
     * @param index 要删除的元素的位置
     */
    public static String[] deleteMemberFromArray(String[] arr, int index)//index为要删除元素的位置
    {
        String[] nArr = new String[arr.length - 1];
        if (index < 0 || index >= arr.length) {
            throw new IllegalArgumentException("The index is out of range");
        } else {
            for (int i = 0; i < arr.length; i++) {

                if (i < index) nArr[i] = arr[i];

                if (i == index) continue;

                if (i > index) nArr[i - 1] = arr[i];
            }
        }
        return nArr;
    }

    /**
     * 数组转换String
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return String
     */
    public static String arrySwithStr(Object[] arr, String separator) {
        if (separator == null) separator = "";
        StringBuilder sb = new StringBuilder();
        for (Object anArr : arr) {
            sb.append(anArr).append(separator);
        }

        if (separator.equals("")) {
            return sb.toString();
        } else {
            return StringUtil.getConstant(sb.toString());
        }
    }

    /**
     * 去除字符串前后两个字符
     *
     * @param str String
     * @return new String 去除后的字符串
     */
    public static String removeChar(String str) {
        return str.substring(1, str.length() - 1);
    }


    /**
     * 用于Hiberenate实体属性名称 转换为sql时候的字段名称
     *
     * 规则：当属性名称字母大写时，在此字母前添加_，并且修改为小写
     *
     * @param attributeName 属性名称字符串
     * @return sql字段名称
     */
    public static String tranFieldName(String attributeName){
        StringBuilder builder = new StringBuilder();

        for (char s : attributeName.toCharArray()) {
            if(Character.isUpperCase(s)){
                builder.append("_").append(Character.toLowerCase(s));
                continue;
            }
            builder.append(s);
        }

        return builder.toString();
    }
}
