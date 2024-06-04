package com.example.springwebtask.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class InsertForm {
    @Length(min = 1,max = 20)
    @NotEmpty
    private String productId;
    @Length(min = 1,max = 255)
    @NotEmpty
    private String productName;
    @Length(min = 1,max = 7)
    @NotEmpty
    private String price;
    @NotEmpty
    private String categoryId;
    @Length(min = 1,max = 2000)
    private String description;
//    private String image;

}
