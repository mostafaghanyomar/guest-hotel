package com.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Controller
@ApiIgnore
public class HomeController {
    @RequestMapping("/")
    public String redirectToSwaggerUIApp(){
        return "redirect:swagger-ui.html";
    }
}
