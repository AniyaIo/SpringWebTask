package com.example.springwebtask.record;

import java.sql.Timestamp;

public record ProductsRecord(int id,
                             String productId,
                             String categoryId,
                             String name,
                             String price,
                             String imagePath,
                             String description,
                             Timestamp createdAt,
                             Timestamp updatedAt) {}
