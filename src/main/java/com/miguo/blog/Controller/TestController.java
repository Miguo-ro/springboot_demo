package com.miguo.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String Indexx(){
        return "index";
    }

    @RequestMapping("/blog")
    public String Blogx(){
        return "blog";
    }
    @RequestMapping("/types")
    public String Types(){
        return "types";
    }
    @RequestMapping("/tags")
    public String tagsx(){
        return "tags";
    }
    @RequestMapping("/archives")
    public String archivesx(){
        return "archives";
    }
    @RequestMapping("/about")
    public String aboutx(){
        return "about";
    }
    @RequestMapping("/blogs")
    public String blogsx(){
        return "admin/blogs";
    }
    @RequestMapping("/blogs-input")
    public String blogsInput(){
        return "admin/blogs-input";
    }


}
