package com.example.springwebtask.controller;

import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.service.IProductService;
import com.example.springwebtask.service.IUsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class UsersController {
    @Autowired
    private HttpSession session;
    @Autowired
    private IUsersService usersService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/index")
    public String LoginForm(@ModelAttribute("login_form") LoginForm loginForm,
                                 Model message) {
        message.addAttribute("login_failed_message","");
        return "/index";
    }
    @PostMapping("/index")
    public String postLoginForm(@Validated @ModelAttribute("login_form") LoginForm loginForm,
                            BindingResult errorResult,
                            Model model) {
        if(errorResult.hasErrors()) {
            return "/index";
        }
        int id = usersService.login(
                loginForm.getLoginId(),
                loginForm.getPassword()
        );
        if(id == (-1)){
            String msg=messageSource.getMessage("login.error",null, Locale.JAPAN);
            //複数メッセージを記載したい場合putを使う事を忘れない
//            Map<String,Integer> map= new HashMap<>();
//            map.put(msg,1);
//            model.addAttribute("loginFailedMessage",map);
            model.addAttribute("loginFailedMessage",msg);
            return "/index";
        }
        session.setAttribute("user", usersService.findById(id));
        return "redirect:/menu";
    }

    @GetMapping("/logout")
    public String Logout(){
        session.invalidate();
        return "/logout";
    }

}
