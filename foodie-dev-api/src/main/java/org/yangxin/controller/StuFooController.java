package org.yangxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.service.StuService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yangxin
 * 2019/11/19 21:45
 */
@ApiIgnore
@RestController
public class StuFooController {
    private final StuService stuService;

    @Autowired
    public StuFooController(StuService stuService) {
        this.stuService = stuService;
    }

    @GetMapping("/getStu")
    public Object getStu(int id) {
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public Object saveStu() {
        stuService.saveStu();
        return "OK";
    }

    @PostMapping("/updateStu")
    public Object updateStu(int id) {
        stuService.updateStu(id);
        return "OK";
    }

    @PostMapping("/deleteStu")
    public Object deleteStu(int id) {
        stuService.deleteStu(id);
        return "OK";
    }
}
