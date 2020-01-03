package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.CommentMapper;
import com.aaa.lee.app.model.Comment;
import com.aaa.lee.app.properties.FtpProperties;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.DateUtil;
import com.aaa.lee.app.utils.FileNameUtil;
import com.aaa.lee.app.utils.FtpUtil;
import com.aaa.lee.app.vo.FileCommentVo;
import com.aaa.lee.app.vo.MyCommentVo;
import com.aaa.lee.app.vo.ProductAllCollectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.common.Mapper;
import org.joda.time.DateTime;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    private FtpProperties ftpProperties;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Mapper<Comment> getMapper() {
        return commentMapper;
    }

    /**
     * 查询我的评价
     * @param memberId
     * @return
     */
    public List<MyCommentVo> listMyComment(Long memberId){
        if (!"".equals(memberId)){
            List<MyCommentVo> myCommentVos = commentMapper.listMyComment(memberId);
            if (myCommentVos.size()>0){
                return myCommentVos;
            }else {
                return null;
            }
        }
        return null;
    }
    /**
     * 查看商品的评价
     * @param productId
     * @param
     * @return
     */
    public List<ProductAllCollectVo> listAllComment(Long productId){
        if (!"".equals(productId) && null != productId){
            List<ProductAllCollectVo> pr = commentMapper.listProductComment(productId);
            if(pr.size()>0){
                return pr;
            }else {
                return null;
            }
        }
        return null;
    }
    /**
     * 添加评价
     * @param fileCommentVo
     * @param
     * @return
     */
    public ResultData insertComment(FileCommentVo fileCommentVo){
        Comment comment = fileCommentVo.getComment();
        ResultData resultData = new ResultData<>();
        comment.setCreateTime(Timestamp.valueOf(DateUtil.getDateNow()));
        if (!"".equals(comment) && null != comment){
            int insert = commentMapper.insert(comment);
            resultData.setCode(StatusEnum.SUCCESS.getCode());
            resultData.setMsg(StatusEnum.EXIST.getMsg());
            return  resultData;
        }else {
            resultData.setCode(StatusEnum.FAILED.getCode());
            resultData.setMsg(StatusEnum.NOT_EXIST.getMsg());
            return resultData;
        }


    }

    /**
     * 上传图片方法
     * @param file
     * @return
     */
    public String upPicture(MultipartFile file){
        String oldName = file.getOriginalFilename();
        try {
            // 3.生成新的文件名(315678163781256812975498)
            String newName = FileNameUtil.getFileName(1L);
            // 4.截取原始文件名的后缀,拼接到新的名称上--->获取新的文件名
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            // 5.获取文件的路径(2019/11/13)
           String filePath = new DateTime().toString("yyyy/MM/dd");
            //TODO 获取文件的路径

            // 6.调用上传文件的工具类
            boolean ifSuccess = FtpUtil.uploadFile(ftpProperties.getIpAddr(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newName, file.getInputStream());

            if(ifSuccess) {
                // 上传成功，把路径保存到数据库中
                Comment comment = new Comment();
                comment.setId(comment.getId());
                String picUrl = ftpProperties.getHttpPath() + "/" + filePath + "/" + newName;
                comment.setPics(picUrl);
                Integer updateResult = commentMapper.updateByPrimaryKey(comment);
                if(updateResult > 0) {
                    return picUrl;
                } else {
                    return null;
                }

            } else {
                return null;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
