package com.example.springwebtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {
    @Autowired
    HttpSession session;
    @GetMapping("/success")
    public String success(Model model){
        var message=(String)session.getAttribute("successMessage");
        model.addAttribute("message",message);
        System.out.println("success:"+message);
        session.removeAttribute("successMessage");
        return "success";
    }
}
