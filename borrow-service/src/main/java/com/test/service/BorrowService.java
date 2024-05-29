package com.test.service;

import com.test.entity.UserBorrowDetail;

public interface BorrowService {
    UserBorrowDetail getUserBorrowDetailByUid(int id);

    boolean doBorrow(int uid, int bid);
}
