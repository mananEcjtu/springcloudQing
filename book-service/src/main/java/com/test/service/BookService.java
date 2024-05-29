package com.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.entity.Book;

public interface BookService extends IService<Book> {

    boolean setRemain(int bid, int count);

    int getRemain(int bid);
}
