package com.miguo.blog.Controller;

import com.miguo.blog.po.Blog;
import com.miguo.blog.service.BlogService;
import com.miguo.blog.service.TypeService;
import com.miguo.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(Model model, BlogQuery blog,
                        @PageableDefault(size = 2,sort = "updateTime",direction = Sort.Direction.DESC)
                                Pageable pageable){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "/admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(Model model, BlogQuery blog,
                        @PageableDefault(size = 2,sort = "updateTime",direction = Sort.Direction.DESC)
                                Pageable pageable){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "/admin/blogs :: blogList";
    }
}
