package com.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("select count from db_book where bid = #{bid}")
    int getRemain(int bid);

    @Update("update db_book set count = #{count} where bid = #{bid}")
    int setRemain(@Param("bid") int bid, @Param("count") int count);
}
