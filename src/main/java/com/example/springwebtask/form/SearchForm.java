package com.example.springwebtask.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SearchForm {
    private String sortRule;
    private String searchWord;
}
