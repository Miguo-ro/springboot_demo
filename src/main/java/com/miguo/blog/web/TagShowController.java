package com.miguo.blog.web;

import com.miguo.blog.po.Tag;
import com.miguo.blog.po.Type;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class TagShowController {
    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model,
                        @PageableDefault(size = 8, page = 0, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable) {
        List<Tag> tagList=tagService.listTagTop(10000);
        if (id == -1) {
            id=tagList.get(0).getId();
        }
        model.addAttribute("tags",tagList);
        model.addAttribute("page",blogService.listBlog(pageable,id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}

