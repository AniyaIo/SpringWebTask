package com.example.springwebtask.record;

import java.sql.Timestamp;

public record UsersRecord(int id,
                            String loginId,
                            String password,
                            String name,
                            int role,
                            Timestamp createdAt,
                            Timestamp updatedAt) {}
