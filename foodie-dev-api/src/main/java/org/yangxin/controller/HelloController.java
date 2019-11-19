package org.yangxin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yangxin
 * 2019/11/19 21:46
 */
@RestController
@ApiIgnore
public class HelloController {
    @GetMapping("/hello")
    public Object hello() {
        return "Hello World!";
    }
}
