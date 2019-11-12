package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.StuMapper;
import org.yangxin.pojo.Stu;
import org.yangxin.service.StuService;

/**
 * @author yangxin
 * 2019/11/12 22:19
 */
@Service
public class StuServiceImpl implements StuService {
    private final StuMapper stuMapper;

    @Autowired
    public StuServiceImpl(StuMapper stuMapper) {
        this.stuMapper = stuMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu() {

    }

    @Override
    public void updateStu(int id) {

    }

    @Override
    public void deleteStu(int id) {

    }
}
