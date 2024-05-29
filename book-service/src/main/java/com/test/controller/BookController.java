package com.test.controller;

import com.test.entity.Book;
import com.test.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService service;

    @RequestMapping("/{bid}")
    public Book findBookById(@PathVariable("bid") int bid) {
        return service.getById(bid);
    }

    @RequestMapping("/remain/{bid}")
    public int bookRemain(@PathVariable("bid") int uid){
        return service.getRemain(uid);
    }

    @RequestMapping("/borrow/{bid}")
    public boolean bookBorrow(@PathVariable("bid") int uid) {
        int remain = service.getRemain(uid);
        return service.setRemain(uid, remain - 1);
    }

}
