package com.springsecurity.controller.systemController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SystemController {

    @GetMapping("/system")
    public String system(HttpServletRequest request) {
        return "system/system";
    }

    @GetMapping("/system/create")
    public String create(HttpServletRequest request) {
        return "system/systemCreate";
    }

    @GetMapping("/system/delete")
    public String delete(HttpServletRequest request) {
        return "system/systemDelete";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}
