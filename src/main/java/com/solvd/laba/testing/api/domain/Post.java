package com.solvd.laba.testing.api.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private Integer id;
    private User user;
    private String title;
    private String body;
}
