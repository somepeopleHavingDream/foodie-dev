package org.yangxin.service;

import org.yangxin.pojo.Carousel;

import java.util.List;

/**
 * 轮播图
 *
 * @author yangxin
 * 2019/11/23 15:44
 */
public interface CarouselService {
    /**
     * 查询所有轮播图列表
     *
     * @param isShow 是否展示
     * @return 轮播图记录
     */
    List<Carousel> queryAll(Integer isShow);
}
