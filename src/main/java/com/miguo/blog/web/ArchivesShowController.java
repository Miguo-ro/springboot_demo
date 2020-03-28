package com.miguo.blog.web;

import com.miguo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesShowController {
    @Autowired
    BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archivemap",blogService.archiveBlog());
        model.addAttribute("blogcount",blogService.countBlog());
        return "archives";
    }
}
