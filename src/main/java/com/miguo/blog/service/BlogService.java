package com.miguo.blog.service;

import com.miguo.blog.po.Blog;
import com.miguo.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable,String query);
    Page<Blog> listBlog(Pageable pageable,Long tagId);


    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Blog getAndConvert(Long id);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();


}
