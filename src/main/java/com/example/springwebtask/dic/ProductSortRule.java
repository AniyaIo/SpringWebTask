package com.example.springwebtask.dic;

public enum ProductSortRule {
    ID_ASC("id ASC"),
    ID_DESC("id DESC"),
    CATEGORY_ASC("category_id ASC"),
    CATEGORY_DESC("category_id DESC"),
    PRICE_ASC("price ASC"),
    PRICE_DESC("price DESC");

    private final String rule;
    ProductSortRule(String rule) {
        this.rule=rule;
    }

    public String getRule(){
        return this.rule;
    }

}