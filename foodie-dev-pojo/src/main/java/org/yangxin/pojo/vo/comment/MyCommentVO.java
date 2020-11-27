package org.yangxin.pojo.vo.comment;

import lombok.Data;

import java.util.Date;

/**
 * @author yangxin
 * 2020/11/27 17:27
 */
@Data
public class MyCommentVO {

    private String commentId;

    private String content;

    private Date createdTime;

    private String itemId;

    private String itemName;

    private String specName;

    private String itemImg;
}
