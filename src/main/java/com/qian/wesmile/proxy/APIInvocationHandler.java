package com.qian.wesmile.proxy;

import com.alibaba.fastjson.JSON;
import com.qian.wesmile.annotation.RelativePath;
import com.qian.wesmile.model.result.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class APIInvocationHandler implements InvocationHandler {
    private static final Logger log = LoggerFactory.getLogger(APIInvocationHandler.class);

    public APIInvocationHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        checkAnnotation(method);

        String result = "";
        //this method access token is different from others
        if (method.getName().equals("oauth2")) {

            result = "";
        } else {
            result = "";
        }

        //String result = doAPIRequest(method, args);
        Class<?> returnType = method.getReturnType();
        if (void.class == returnType) {
            return null;
        }
        try {
            //TODO 这里无法正常解析出数据到底是返回一个属性是空的对象还是直接招聘运行时异常有等商榷
            APIResult apiResult = JSON.parseObject(result, APIResult.class);
            if (apiResult.success()) {
                return JSON.parseObject(result, returnType);
            } else {
                throw new RuntimeException(result + " can't parse to {}" + returnType);
            }
        } catch (Exception e) {
            throw new RuntimeException(result + " can't parse to {}" + returnType);
        }
    }

    private void checkAnnotation(Method method) {
        if (!method.isAnnotationPresent(RelativePath.class)) {
            throw new RuntimeException(method.toString() + " must with annotation ＠RelativePath ");
        }
    }
}
