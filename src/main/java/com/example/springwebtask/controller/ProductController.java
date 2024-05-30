package com.example.springwebtask.controller;

import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.record.UsersRecord;
import com.example.springwebtask.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    private HttpSession session;
    @Autowired
    private IProductService productService;

    @GetMapping("/menu")
    public String menu(Model model){
//        model.addAttribute("productList",productService.findAll());
        return "menu";
    }
}
