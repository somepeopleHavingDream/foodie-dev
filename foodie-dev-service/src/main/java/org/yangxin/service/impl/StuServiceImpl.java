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
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStu() {
        Stu stu = Stu.builder()
                .name("jack")
                .age(19)
                .build();
        stuMapper.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStu(int id) {
        Stu stu = Stu.builder()
                .id(id)
                .name("lucy")
                .age(20)
                .build();
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
