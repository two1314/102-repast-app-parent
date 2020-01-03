package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FileCommentVo extends Comment implements Serializable {
    private String token;
    private Comment comment;
}
