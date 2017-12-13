package com.jt.blog.mapper;

import com.jt.blog.model.Blog;
import com.jt.blog.mybatis.common.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper extends MyMapper<Blog> {

    @Select("SELECT * FROM blog WHERE ID IN " +
            "(SELECT CASE WHEN SIGN(ID - #{id}) > 0 THEN MIN(ID) WHEN SIGN(ID - #{id}) < 0 THEN MAX(ID) END AS ID FROM blog WHERE ID <> #{id} AND personal=0  GROUP BY SIGN(ID - #{id}) ORDER BY SIGN(ID - #{id}))")
    List<Blog> getPrevAndNextBlog(Long id);
}