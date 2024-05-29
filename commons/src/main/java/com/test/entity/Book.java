package com.test.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

    @TableId
    private int bid;

    private String title;

    private String note;

}
