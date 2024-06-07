package com.example.springwebtask.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DeleteForm {
//    @Size(min = 1,max=20)
    private int id;
}
