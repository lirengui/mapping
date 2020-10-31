package com.csair.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LiRenGui
 * @date 2020年10月31日18:30:12
 */

@RestController
@RequestMapping("/bookShop")
public class MapsController {

    @GetMapping("/getAvPrice")
    public String getAvPrice() {
        return "Hello";
    }
}

