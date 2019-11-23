package org.yangxin.mapper;

import org.yangxin.pojo.Carousel;

import java.util.List;

public interface CarouselMapper {
    int deleteByPrimaryKey(String id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    /**
     * 通过isShow字段，查询所有记录
     *
     * @param isShow 是否展示
     * @return 轮播图记录
     */
    List<Carousel> selectByIsShow(Integer isShow);
}