package com.test.feign;

import com.test.entity.User;
import com.test.feign.impl.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userserver", fallback = UserClientFallback.class)
public interface UserClient {

    @RequestMapping("/user/{uid}")
    User findUserById(@PathVariable("uid") int uid);

    @RequestMapping("/user/borrow/{uid}")
    boolean userBorrow(@PathVariable("uid") int uid);

    @RequestMapping("/user/remain/{uid}")
    int userRemain(@PathVariable("uid") int uid);

}
