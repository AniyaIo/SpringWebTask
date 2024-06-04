package com.example.springwebtask.controller;

import com.example.springwebtask.dic.ProductSortRule;
import com.example.springwebtask.form.InsertForm;
import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.form.SearchForm;
import com.example.springwebtask.record.ProductsRecord;
import com.example.springwebtask.record.UsersRecord;
import com.example.springwebtask.service.ICategoriesService;
import com.example.springwebtask.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private HttpSession session;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping("/menu")
    public String menu(@ModelAttribute("searchForm")SearchForm searchForm,
                       Model model){
        List<ProductsRecord> result;
        String getFormRule=searchForm.getSortRule();
        if(getFormRule != null) {
            ProductSortRule rule = ProductSortRule.valueOf(getFormRule);
            result = productService.findByName(
                    searchForm.getSearchWord(),
                    rule.getRule());
        }else {
            result = productService.findByName(
                    searchForm.getSearchWord(),
                    "");
        }
        model.addAttribute("productList",result);
        model.addAttribute("productCount",result.size());
        return "menu";
    }

    @GetMapping("/insert")
    public String insert(@ModelAttribute("insertForm") InsertForm insertForm,
                         Model model){
        model.addAttribute("categoryList",categoriesService.findAll());
        System.out.println("insert access");
        return "insert";
    }

    @PostMapping("/insert")
    public String postInsert(@Validated @ModelAttribute("insertForm") InsertForm insertForm,
                             BindingResult errorResult){
        if(errorResult.hasErrors()) {
            return "insert";
        }
        return "redirect:/menu";
    }
}
