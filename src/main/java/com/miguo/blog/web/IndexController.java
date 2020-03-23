package com.miguo.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/error")
    public String index(){
//        int i=1/0;/*/
//        String blog=null;
//        if (blog==null){
//            throw new NotFoundException("博客不存在");
//        }
//        System.out.println("------------index---------------");
        return "index";
    }
}
