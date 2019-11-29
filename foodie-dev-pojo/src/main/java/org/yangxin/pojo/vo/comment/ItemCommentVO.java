package org.yangxin.pojo.vo.comment;

import lombok.Data;

import java.util.Date;

/**
 * 商品评价
 *
 * @author yangxin
 * 2019/11/28 10:53
 */
@Data
public class ItemCommentVO {
    /**
     * 评价等级
     */
    private int commentLevel;

    /**
     * 内容
     */
    private String content;

    /**
     * 规格名
     */
    private String specName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 用户头像
     */
    private String userFace;

    /**
     * 昵称
     */
    private String nickname;
}
