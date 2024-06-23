package com.inje.lublio.blog.controller;

import com.inje.lublio.blog.dto.ArticleAddRequest;
import com.inje.lublio.blog.dto.ArticleResponse;
import com.inje.lublio.blog.dto.ArticleUpdateRequest;
import com.inje.lublio.blog.model.Article;
import com.inje.lublio.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    /***
     * Add an article
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleAddRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    /***
     * Find all articles
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    /***
     * Find all articles
     */
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    /***
     * Update an article
     */
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody ArticleUpdateRequest request) {
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }

    /***
     * Delete an article
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

}
