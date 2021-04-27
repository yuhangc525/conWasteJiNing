package cn.edu.bjtu.jzlj.util.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Description: validator 检验类
 * @Author lj
 * @Date 2019/10/18 17:46
 */
@Component
public class ValidatorUtil implements InitializingBean {
    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator 通过工厂的初始化方式实例
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
    * @Description: 校验并返回校验结果
    * @Author: lj
    * @Date 2019/10/18 17:50
    * @param bean 待校验的类
    * @return com.main.util.validator.ValidationResult  校验结果
    * @throws:
    **/
    public ValidationResult validateBean(Object bean) throws Exception {
        if(StringUtils.isEmpty(bean)){
            throw new Exception("bean为空或不合法");
        }
        //校验结果
        ValidationResult validationResult = new ValidationResult();
        //校验
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        //有错误
        if(!constraintViolationSet.isEmpty()){
            validationResult.setHasError(true);
            //遍历输出错误
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                validationResult.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return validationResult;
    }

}
