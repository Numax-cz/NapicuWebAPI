package com.napicu.napicuwebapi.Error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
public class NapicuApiErrorController implements ErrorController {


    public String getErrorPath(){
        return "/error";
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "redirect:/";
    }
}
