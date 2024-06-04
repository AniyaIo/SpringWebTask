package com.example.springwebtask.record;

import java.sql.Timestamp;

public record CategoriesRecord(int id,
                               String name,
                               Timestamp createdAt,
                               Timestamp updatedAt) {}
