package com.inje.lublio.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleUpdateRequest {

    private String author;
    private String title;
    private String content;

}
