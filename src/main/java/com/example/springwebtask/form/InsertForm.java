package com.example.springwebtask.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class InsertForm {
    @Length(min = 1,max = 20)
    @NotEmpty(message = "商品コードは必須です")
    private String productId;
    @Length(min = 1,max = 255)
    @NotEmpty(message = "商品名は必須です")
    private String productName;
    @Length(min = 1,max = 7)
    @NotEmpty(message = "単価は必須です")
    private String price;
    @NotEmpty(message = "カテゴリは必須です")
    private String categoryId;
    @Length(max = 2000)
    private String description;
//    private String image;

}
