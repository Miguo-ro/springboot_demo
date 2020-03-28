package com.miguo.blog.web;

import com.miguo.blog.po.Type;
import com.miguo.blog.service.BlogService;
import com.miguo.blog.service.TypeService;
import com.miguo.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model,
                        @PageableDefault(size = 8, page = 0, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable) {
        List<Type> typesList = typeService.listTypeTop(1000);
        if (id == -1) {
            id=typesList.get(0).getId();
        }
        BlogQuery blogQuery=new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",typesList);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}

