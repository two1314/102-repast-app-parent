package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Collect;
import com.aaa.lee.app.model.Comment;
import com.aaa.lee.app.model.Product;
import com.aaa.lee.app.vo.MyCommentVo;
import com.aaa.lee.app.vo.ProductAllCollectVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentMapper extends Mapper<Comment> {
    Comment listAll(Long order_id);

    List<ProductAllCollectVo> listProductComment(Long productId);

    List<MyCommentVo> listMyComment(Long memberId);

}