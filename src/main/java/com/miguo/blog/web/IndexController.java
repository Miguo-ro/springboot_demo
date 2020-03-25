package com.miguo.blog.web;

import com.miguo.blog.service.BlogService;
import com.miguo.blog.service.TagService;
import com.miguo.blog.service.TypeService;
import com.miguo.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(10));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){

        model.addAttribute("blog",blogService.getBlog(id));
        return "blog";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @PostMapping("/search")
    public String search(@RequestParam @PathVariable String query, Model model,
                         @PageableDefault(size = 8,page = 0,sort = {"updateTime"},direction = Sort.Direction.DESC)
                    Pageable pageable
                         ){
        model.addAttribute("page",blogService.listBlog(pageable,"%"+query+"%"));
        model.addAttribute("query",query);
        return "search";

    }


}
