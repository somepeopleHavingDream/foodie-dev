package org.yangxin.service;

import org.yangxin.pojo.Stu;

/**
 * 用于测试Restful风格的Api
 *
 * @author yangxin
 * 2019/11/12 22:16
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface StuService {
    Stu getStuInfo(int id);

    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);
}
