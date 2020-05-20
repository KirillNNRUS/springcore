package com.medoshin.lectures.javalab.springcore.controller;

import com.medoshin.lectures.javalab.springcore.component.Prototype;
import com.medoshin.lectures.javalab.springcore.component.RequestBean;
import com.medoshin.lectures.javalab.springcore.entity.User;
import com.medoshin.lectures.javalab.springcore.services.TestService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Provider;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/exception-handler-annotation.html
@RestController
class DemoController {
    @Autowired
    private TestService testService;

    @Autowired
    private Provider<RequestBean> requestBeanProvider;

//    @Autowired
//    private RequestBean reqBean;

    @RequestMapping(path = "/test/{id}/{name}", method = RequestMethod.GET, params = {"color"})
    public String getHello(@RequestHeader HttpHeaders headers,
                           @PathVariable(name = "name") String fullName,
                           @PathVariable String id,
                           @RequestParam(name = "color", required = false) String color,
                           @RequestParam(name = "size", required = false) Integer size,
                           HttpServletRequest req,
                           HttpServletResponse resp,
                           HttpSession session,
                           Prototype prototype,
                           RequestBean reqBeanByDirectInject) {
        Cookie cookie = new Cookie("foo", "123");
        cookie.setMaxAge(60);
//        cookie.setSecure(true);
        cookie.setVersion(1000);
        resp.setHeader("Cache-Control", "max-age=200000");
        resp.setHeader("Connection", "Transfer-Encoding");
        resp.addCookie(cookie);
        System.out.println("-----reqBeanByDirectInject: " + reqBeanByDirectInject.getClass());
        System.out.println("-------requestBeanProvider" + requestBeanProvider.get().getClass());
        System.out.println("-----reqBeanByDirectInject id: " + reqBeanByDirectInject.getUuid());
        System.out.println("-------requestBeanProvider id: " + requestBeanProvider.get().getUuid());

//        System.out.println("-------------req bean" + reqBean.getClass());

        System.out.println("----------from test service req bean id: " + testService.getRequestBeanId());

        return "Hello!!!!";
    }

    @RequestMapping(path = "/test/{id}/{name}", method = RequestMethod.GET)
    public String getHello2() {
        return "Hello from 2 method!!";
    }

    @GetMapping(path = "/test-err-num-format")
    public String createErrNumberFormat() {
        throw new NumberFormatException();
    }

    @GetMapping(path = "/test-err-not-found")
    public String createErrNotFound() throws NotFoundException {
        throw new NotFoundException("err");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public String handleNumberFormatException(Throwable ex) {
        System.out.println("------------ex: " + ex);
        return "Error";
    }
}
