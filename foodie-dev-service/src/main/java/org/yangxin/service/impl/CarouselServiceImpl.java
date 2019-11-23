package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.mapper.CarouselMapper;
import org.yangxin.pojo.Carousel;
import org.yangxin.service.CarouselService;

import java.util.List;

/**
 * 轮播图接口实现
 *
 * @author yangxin
 * 2019/11/23 15:48
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    private final CarouselMapper carouselMapper;

    @Autowired
    public CarouselServiceImpl(CarouselMapper carouselMapper) {
        this.carouselMapper = carouselMapper;
    }

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        return carouselMapper.selectByIsShow(isShow);
//        return null;
    }
}
