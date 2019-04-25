package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息
        //HttpServerletResponse 装响应信息
        Cookie cookie =  new Cookie("login","true");
        response.addCookie(cookie);

        return "恭喜你获取cookies信息成功！";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies才能访问的get请求
     */

    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "带cookies请求",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你必须携带cookies来访问！";
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                return "这是一个需要携带cookies才能访问的get请求!";
            }
        }

        return "你必须携带cookies来访问！";

    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式
     */

    @RequestMapping(value = "/getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "带参数请求",httpMethod = "GET")
    public String getWithParam(@RequestParam Integer page,
                               @RequestParam Integer pagesize){
        return "参数访问成功！";
    }

    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    @ApiOperation(value = "带参数请求list",httpMethod = "GET")
    public Map<String,Integer> getlist(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> mylist = new HashMap<>();
        mylist.put("cup",100);
        mylist.put("drink",20);
        mylist.put("shoes",200);

        return mylist;
    }

    /**
     * 第二种实现方式
     * url:ip:port/getlist2/start/end
     */

    @RequestMapping(value = "/getlist2/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "使用PathVariable方法带参数请求list2",httpMethod = "GET")
    public Map getlist2(@PathVariable Integer start,
                        @PathVariable Integer end){
        Map<String,Integer> mylist = new HashMap<>();
        mylist.put("cup",100);
        mylist.put("drink",20);
        mylist.put("shoes",200);

        return mylist;
    }
}
