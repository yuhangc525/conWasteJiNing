package cn.edu.bjtu.jzlj.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.stream.IntStream;

/**
 * @program: jzlj
 * @description: 通用工具类
 * @author: ld
 * @create: 2019-10-15 08:16
 */
public class CommonUtil
{


    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /*
    *   排序规则：降序
    */
    public static final String ORDER_DESC = "desc";
    /**
     * 排序规则：升序
     */
    public static final String ORDER_ASC = "asc";

    public static final String convertToHexString(byte[] paramArrayOfByte)
    {
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
        {
            return "";
        }
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfByte.length; ++i)
        {
            localStringBuffer.append(convertToHexString(paramArrayOfByte[i]));
        }
        return localStringBuffer.toString();
    }

    public static final String convertToHexString(byte paramByte)
    {
        int i = paramByte;
        if (i < 0)
        {
            i += 256;
        }
        return hexDigits[(i / 16)] + hexDigits[(i % 16)];
    }


    /* *
     * @Author: ld
     * @Description: 字符串MD5加密
     * @Date 2019/10/15 8:18
     * @Param paramString
     * @return java.lang.String
     * @throws:
     **/
    public static String EncryptMD5(String paramString)
    {
        String str1 = null;
        try
        {
            String str2 = paramString;
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.reset();
            str1 = convertToHexString(localMessageDigest.digest(str2
                    .getBytes()));
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return str1;
    }

    /**
     * @Author: ld
     * @Description: 通过图片的文件名称，获取两位整数路径字符
     * @Date 2019/10/15 8:33
     * @Param picUUID
     * @return java.lang.String
     * @throws:
     **/
    public static String getIntDir(String picUUID)
    {
        String result = "";
        if(picUUID != null && picUUID.length() >0)
        {
            String MD5Str = EncryptMD5(picUUID);
            MD5Str = MD5Str.substring(0,2);
            long b = Integer.parseInt(MD5Str, 16);
            int intRet =(int)(b%100);

            result = String.valueOf(intRet);
        }
        return result;
    }

    /**
    * @Author: Administrator
    * @Description: 驼峰转下划线
    * @Date 2020/11/8 17:30
    * @Param value待转换值
    * @return java.lang.String 结果
    * @throws:
     **/
    public static String camelToUnderscore(String value)
    {
        if (org.apache.commons.lang3.StringUtils.isBlank(value))
        {
            return value;
        }
        String[] arr = org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0)
        {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1)
                result.append(arr[i]).append("_");
            else
                result.append(arr[i]);
        });
        return StringUtils.lowerCase(result.toString());
    }
}