package com.medoshin.lectures.javalab.springcore._testProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CGLibProxyTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        A a = new A();

        IA dynProxy = (IA) Proxy.newProxyInstance(
                A.class.getClassLoader(),
                a.getClass().getInterfaces(),
                new Handler(a)
        );

        System.out.println("call A.getName() by dyn: " + dynProxy.getName());
        System.out.println("call A.getClass() by dyn: " + dynProxy.getClass());
        System.out.println("dynProxy is Proxy: " + Proxy.isProxyClass(dynProxy.getClass()));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(A.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> doHandle(o, method));
        IA cglibProxy = (IA) enhancer.create();
        System.out.println("call A.getClass() by CGLib: " + cglibProxy.getClass());
        System.out.println("call A.getName() by CGLib: " + cglibProxy.getName());
        System.out.println("cglibProxy is Proxy: " + Proxy.isProxyClass(cglibProxy.getClass()));

        Method[] methods = OtherClass.class.getMethods();
        Method otherMethod = null;
        for (Method m : methods) {
            if (m.getName().equals("otherMethod")) {
                otherMethod = m;
                break;
            }
        }

        assert otherMethod != null;
        OtherClass otherClass = new OtherClass();
        otherMethod.invoke(otherClass);
    }

    private static Object doHandle(Object o, Method method) {
        if (method.getName().equals("getClass")) {
            return "oooooooo";
        }
        if (method.getName().equals("getName")) {
            return "oooooooo";
        }
        if (method.getName().equals("otherMethod")) {
            return "dfdf";
        }
        return null;
    }
}

class A implements IA {
    private String name;

    public A() {
        this.name = "Name";
    }

    public A(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}

interface IA {
    public String getName();

    public void setName(String name);
}

class Handler implements InvocationHandler {
    private Object object;

    public Handler(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getClass")) {
            return "oooooooo";
        }
        if (method.getName().equals("getName")) {
            return "ooooo";
        }
        return null;
    }
}

class OtherClass implements IA {
    public void otherMethod() {
        System.out.println("In other method");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}
