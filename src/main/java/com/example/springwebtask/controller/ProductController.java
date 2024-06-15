package com.example.springwebtask.controller;

import com.example.springwebtask.dic.ProductSortRule;
import com.example.springwebtask.form.DeleteForm;
import com.example.springwebtask.form.InsertForm;
import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.form.SearchForm;
import com.example.springwebtask.record.ProductsRecord;
import com.example.springwebtask.record.UsersRecord;
import com.example.springwebtask.service.ICategoriesService;
import com.example.springwebtask.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {
    @Autowired
    private HttpSession session;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoriesService categoriesService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/menu")
    public String menu(@ModelAttribute("searchForm")SearchForm searchForm,
                       Model model){
        List<ProductsRecord> productData;
        List<ProductsRecord> result=new ArrayList<>();
        String getFormRule=searchForm.getSortRule();
        //productテーブルからデータ取得
        if(getFormRule != null) {
            ProductSortRule rule = ProductSortRule.valueOf(getFormRule);
            productData = productService.findByName(
                    searchForm.getSearchWord(),
                    rule.getRule());

        }else {
            productData = productService.findByName(
                    searchForm.getSearchWord(),
                    "");
        }
        //categoryIdをcategoryNameに変換
        for(var p:productData){
            result.add(
                    new ProductsRecord(
                            p.id(),
                            p.productId(),
                            categoriesService.getNameFromId(Integer.parseInt(p.categoryId())), //nameに変更
                            p.name(),
                            p.price(),
                            p.imagePath(),
                            p.description(),
                            p.createdAt(),
                            p.updatedAt()
                    )
            );
        }

        model.addAttribute("productList",result);
        model.addAttribute("productCount",result.size());
        model.addAttribute("successMessage","");

        return "menu";
    }
    @GetMapping("/insert")
    public String insert(@ModelAttribute("insertForm") InsertForm insertForm,
                         Model model){
        var sessionData=(UsersRecord)session.getAttribute("user");
        if(sessionData.role()!=1){
            return "redirect:/menu";
        }
        model.addAttribute("categoryList",categoriesService.findAll());
        return "insert";
    }
    @Transactional
    @PostMapping("/insert")
    public String postInsert(@Validated @ModelAttribute("insertForm") InsertForm insertForm,
                             BindingResult errorResult,
                             Model model){
        model.addAttribute("categoryList",categoriesService.findAll());
        model.addAttribute("url","menu");
        if(errorResult.hasErrors()) {
            return "insert";
        }
        try {
            productService.insert(new ProductsRecord(
                    1,
                    insertForm.getProductId(),
                    insertForm.getCategoryId(),
                    insertForm.getProductName(),
                    insertForm.getPrice(),
                    "",
                    insertForm.getDescription(),
                    new Timestamp(0),
                    new Timestamp(0)));
        }catch (DuplicateKeyException e){
            //message.propertiesを使う為
            String msg=messageSource.getMessage("insert.error",null, Locale.JAPAN);
            model.addAttribute("insertFailedMessage",msg);
            return "/insert";
        }

        session.setAttribute("successMessage","登録に成功しました");
        return "redirect:/success";
    }

    ////////////////POSTに変更する//////////////
    @Transactional
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") int id,
                            @ModelAttribute("deleteForm")DeleteForm deleteForm,
                            Model model){
        var productData=productService.findById(id);
        deleteForm.setId(id);
        model.addAttribute("product",productData);
        model.addAttribute("category",categoriesService.getNameFromId(Integer.parseInt(productData.categoryId())));
        return ("detail");
    }

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") int id,
//                         @ModelAttribute("deleteForm")DeleteForm deleteForm){
//        //SQL Exceptionをcatchする
//        productService.delete(id);
//        session.setAttribute("successMessage","削除に成功しました");
//        return "redirect:/success";
//    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("deleteForm")DeleteForm deleteForm){
        //SQL Exceptionをcatchする
        productService.delete(deleteForm.getId());
        session.setAttribute("successMessage","削除に成功しました");
        return "redirect:/success";
    }

    @GetMapping("/updateInput/{id}")
    public String edit(@ModelAttribute("updateForm")InsertForm updateForm,
                       @PathVariable("id") int id,
                       Model model){
        model.addAttribute("errorMessage","");
        var sessionData=(UsersRecord)session.getAttribute("user");
        if(sessionData.role()!=1){
            return "redirect:/menu";
        }
        var productData=productService.findById(id);
        updateForm.setProductId(productData.productId());
        updateForm.setProductName(productData.name());
        updateForm.setPrice(productData.price());
        updateForm.setCategoryId(productData.categoryId());
        updateForm.setDescription(productData.description());
        model.addAttribute("id",id);
        model.addAttribute("categoryList",categoriesService.findAll());
        return "updateInput";
    }

//    エラーメッセージ出力する
    @PostMapping("/updateInput/{id}")
    public String postEdit(@Validated @ModelAttribute("updateForm")InsertForm updateForm,
                           BindingResult bindingResult,
                           @PathVariable("id") int id,
                           Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("id",id);
            model.addAttribute("categoryList",categoriesService.findAll());
            return "updateInput";
        }
//        更新処理を入れる
        try {
            productService.update(new ProductsRecord(
                            id,
                            updateForm.getProductId(),
                            updateForm.getCategoryId(),
                            updateForm.getProductName(),
                            updateForm.getPrice(),
                            "",
                            updateForm.getDescription(),
                            new Timestamp(0),
                            new Timestamp(0)
                    )
            );
        }catch (DuplicateKeyException e){
            model.addAttribute("errorMessage","商品コードが重複しています");
            model.addAttribute("id",id);
            model.addAttribute("categoryList",categoriesService.findAll());
            return "updateInput";
        }
        model.addAttribute("errorMessage","");
        session.setAttribute("successMessage","更新に成功しました");
        return "redirect:/success";
    }
}
