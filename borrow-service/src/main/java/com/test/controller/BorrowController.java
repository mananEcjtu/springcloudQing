package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
        return borrowService.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/borrow2/{uid}")
    @SentinelResource(value = "findUserBorrow2", blockHandler = "test")
    public UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid) throws InterruptedException {
        //Thread.sleep(1000);
        throw new RuntimeException();
        //return borrowService.getUserBorrowDetailByUid(uid);
    }

    public UserBorrowDetail test(int uid, BlockException e) {
        System.out.println(e);
        return new UserBorrowDetail(new User(), Collections.emptyList());
    }

    @RequestMapping("/blocked")
    JSONObject blocked() {
        JSONObject object = new JSONObject();
        object.put("code", 403);
        object.put("success", false);
        object.put("massage", "您的请求频率过快，请稍后再试！");
        return object;
    }

/*    @RequestMapping("/test")
    @SentinelResource(value = "test",
            fallback = "except",    //fallback指定出现异常时的替代方案
            exceptionsToIgnore = IOException.class, //忽略那些异常，也就是说这些异常出现时不使用替代方案
            blockHandler = "block")  // 限流后方法
    String test(){
        // throw new RuntimeException("HelloWorld！");
        return "test!";
    }

    // 替代方法和原方法返回值一致
    public String except(Throwable t) {
        return t.getMessage();
    }

    public String block(BlockException e) {
        return "被限流了";
    }*/

/*    @RequestMapping("/test")
    @SentinelResource("test")
        //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c", required = false) String c) {
        return "请求成功！a = " + a + ", b = " + b + ", c = " + c;
    }*/

    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") int uid,
                      @PathVariable("bid") int bid){
        borrowService.doBorrow(uid, bid);

        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("success", false);
        object.put("message", "借阅成功！");
        return object;
    }

}
