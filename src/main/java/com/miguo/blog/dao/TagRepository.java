package com.miguo.blog.dao;

import com.miguo.blog.po.Tag;
import com.miguo.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

}
