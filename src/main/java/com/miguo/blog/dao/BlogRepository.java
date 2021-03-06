package com.miguo.blog.dao;

import com.miguo.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(Pageable pageable,String query);


    @Modifying
    @Query("UPDATE Blog b SET b.views=b.views+1 where b.id=?1 ")
    int updateViews(Long id);

    @Query("SELECT function('date_format',b.updateTime,'%Y') AS year FROM Blog b GROUP BY year ORDER BY function('date_format',b.updateTime,'%Y') DESC")
    List<String> findGroupYears();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1 ")
    List<Blog> findByYear(String year);
}
