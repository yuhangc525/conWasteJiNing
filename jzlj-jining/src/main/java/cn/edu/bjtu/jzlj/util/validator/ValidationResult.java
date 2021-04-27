package cn.edu.bjtu.jzlj.util.validator;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: validator 结果
 * @Author lj
 * @Date 2019/10/18 17:22
 */
public class ValidationResult {
    /**  有否错误  */
    private boolean hasError = false;

    /** 存储错误的map  */
    private Map<String,String> errorMsgMap = new HashMap<>();

    //获取错误信息，并转换Map---》String
    public String getErrorMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }


    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
