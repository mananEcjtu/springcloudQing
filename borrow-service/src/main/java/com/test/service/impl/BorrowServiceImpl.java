package com.test.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.feign.BookClient;
import com.test.feign.UserClient;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private UserClient userClient;

    @Resource
    private BookClient bookClient;

    @Override
    @SentinelResource(value = "getBorrow")
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        LambdaQueryWrapper<Borrow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Borrow::getUid, uid);
        List<Borrow> borrows = borrowMapper.selectList(wrapper);
        User user = userClient.findUserById(uid);

        List<Book> bookList = borrows.stream().
                map(borrow -> bookClient.findBookById(borrow.getBid())).
                collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }

    @Override
    @GlobalTransactional
    public boolean doBorrow(int uid, int bid) {
        //1. 判断图书和用户是否都支持借阅
        if(bookClient.bookRemain(bid) < 1)
            throw new RuntimeException("图书数量不足");
        if(userClient.userRemain(uid) < 1)
            throw new RuntimeException("用户借阅量不足");
        //2. 首先将图书的数量-1
        if(!bookClient.bookBorrow(bid))
            throw new RuntimeException("在借阅图书时出现错误！");
        //3. 添加借阅信息
        LambdaQueryWrapper<Borrow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Borrow::getUid, uid).eq(Borrow::getBid, bid);
        Borrow borrow = borrowMapper.selectOne(wrapper);
        if(borrow != null)
            throw new RuntimeException("此书籍已经被此用户借阅了！");
        if(borrowMapper.insert(new Borrow(uid, bid)) <= 0)
            throw new RuntimeException("在录入借阅信息时出现错误！");
        //4. 用户可借阅-1
        if(!userClient.userBorrow(uid))
            throw new RuntimeException("在借阅时出现错误！");
        //完成
        return true;
    }

    public UserBorrowDetail blocked(int uid, BlockException e) {
        log.error("uid: {}", uid);
        log.error("error: {}", e);
        return new UserBorrowDetail(null, Collections.emptyList());
    }

}
