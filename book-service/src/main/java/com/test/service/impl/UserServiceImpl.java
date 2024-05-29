package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.entity.Book;
import com.test.mapper.BookMapper;
import com.test.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public boolean setRemain(int bid, int count) {
        return getBaseMapper().setRemain(bid, count) > 0;
    }

    @Override
    public int getRemain(int bid) {
        return getBaseMapper().getRemain(bid);
    }

}
