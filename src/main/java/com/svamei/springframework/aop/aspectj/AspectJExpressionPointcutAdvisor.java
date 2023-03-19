package com.svamei.springframework.aop.aspectj;

import com.svamei.springframework.aop.Pointcut;
import com.svamei.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @ClassName AspectJExpressionPointcutAdvisor
 * @Description
 * @Author Svamei
 * @Date 18:56 2023/3/18
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}