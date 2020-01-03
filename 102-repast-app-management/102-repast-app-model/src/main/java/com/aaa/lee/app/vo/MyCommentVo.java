package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.Comment;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class MyCommentVo extends Comment implements Serializable {
    private String name;
    private String pic;
    private BigDecimal price;
    private Integer star;
    private String content;
    private String pics;
    private Date create_time;
}
