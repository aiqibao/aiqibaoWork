package com.aiqibaowork.aop;

import com.aiqibaowork.annotation.OpLog;
import com.aiqibaowork.constant.OpType;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author:aiqibao
 * @Date:2020/11/9 14:46
 * Best wish!
 */
@Aspect
@Component
public class OpLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpLogAspect.class) ;
    @Around("@annotation(com.aiqibaowork.annotation.OpLog)")
    public Object log(ProceedingJoinPoint pjp) throws Exception{
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        OpLog opLog = method.getAnnotation(OpLog.class) ;
        Object response = null ;
        try {
            response = pjp.proceed() ;
        }catch (Throwable throwable){
            throw new Exception(throwable) ;
        }
        if (StringUtils.isNotEmpty(opLog.opItemIdExpression())){
            SpelExpressionParser parser = new SpelExpressionParser() ;
            Expression expression = parser.parseExpression(opLog.opItemIdExpression()) ;
            EvaluationContext context = new StandardEvaluationContext() ;
            //获取参数值
            Object[] args = pjp.getArgs() ;
            LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method) ;
            //将参数绑定到context中
            if (parameterNames != null){
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i],args[i]);
                }
            }
            //将方法的resp当做变量放到context中，变量名称为该类名转化为小写字母开头的驼峰形式
            if (response != null){
                context.setVariable(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,response.getClass().getSimpleName()),response);
            }
            //解析表达式，获取结果
            String itemId = String.valueOf(expression.getValue(context)) ;
            //执行日志记录
            handle(opLog.opType(),opLog.opItem(),itemId);
        }
        return response ;
    }

    private void handle(OpType opType,String opItem,String opItemId){
        //通过日志打印输出
        LOGGER.info("opType= " + opType.name() + ",opItem = " + opItem + ",opItemId = " + opItemId);
    }
}
