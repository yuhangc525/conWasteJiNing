package cn.edu.bjtu.jzlj.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtils
 * @Description: java常用正则表达式验证工具类
 * @Author
 * @Date 2019/10/30 10:18
 **/
@Component
public class RegexUtils {
    private static final int PWD_LENGTH = 6;


    /** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str)throws Exception {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str) throws Exception {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            throw new Exception("手机号不合法！");
        }
        return true;
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhoneLegal(String str)throws Exception {
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            throw new Exception("手机号不合法！");
        }
        return true;
    }

    /**
     * 验证Email
     * @param email email地址，格式：zhang@gmail.com，zhang@xxx.com.cn，xxx代表邮件服务商
     *              @ 前至少有三位
     * @return 验证成功返回true，验证失败返回false
     */
    public static void checkEmail(String email) throws Exception {
//        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new Exception("邮箱不合法！");
        }

    }


    /**
    * @Description: 截取手机号后6位作为明文密码
    * @Author: lj
    * @Date 2019/10/30 13:20
    * @param phoneNum  String类型手机号
    * @return java.lang.String  手机号后6位
    * @throws:
    **/
    public static String subStringMobilePhoneNum(String phoneNum) throws Exception {
        if(StringUtils.isEmpty(phoneNum)){
            throw new Exception("手机号获取失败");
        }
        int phoneNumLength = phoneNum.length();
        String phoneSub = phoneNum.substring(phoneNumLength-PWD_LENGTH,phoneNumLength);
        return phoneSub;
    }

    /** @Author: LXYuuuuu
     * @Description: 验证车牌号
     * @Date 2019/11/25 16:50
     * @Param chePaiHao
     * @return boolean
     * @throws:
     **/
    public static void checkLicenseNum(String chePaiHao) throws Exception {
        String pattern = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(chePaiHao);
        if (!m.matches()) {
            throw new Exception("车牌号不合法！");
        }
//        String regex1 = "^[\u4E00-\u9FA5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$";
//        String regex2 = "^使[a-zA-Z_0-9]{6}$";
//        String regex3 = "^(BL)[a-zA-Z_0-9]{5}$";
//        return match(pattern, chePaiHao);
    }
    private final static boolean match(String regex, String str) {
        if (null == str || "".equals(str)) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }




    /**
     * @Description:  身份证号正则校验
     * @Author: lenovo
     * @Date 2019/11/25 13:55
     * @param IDNumber  18位身份证号
     * @return boolean  true 为有效身份证
     * @throws:
     **/
    public static boolean validatorIdCard(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                                "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        String val = "京V00005";//~a76hqkggq8hqhh
//        String val = "";//~a76hqkggq8hqhh
        String result= null;
        try {
            checkLicenseNum(val);
            System.err.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
