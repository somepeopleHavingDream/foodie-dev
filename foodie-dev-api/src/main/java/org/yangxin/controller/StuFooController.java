package org.yangxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.service.StuService;

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
}
