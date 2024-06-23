package com.inje.lublio.blog.dto;

import com.inje.lublio.blog.model.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getArticle_id();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
