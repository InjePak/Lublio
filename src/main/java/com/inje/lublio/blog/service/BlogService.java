package com.inje.lublio.blog.service;

import com.inje.lublio.blog.dto.ArticleAddRequest;
import com.inje.lublio.blog.dto.ArticleUpdateRequest;
import com.inje.lublio.blog.model.Article;
import com.inje.lublio.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    /***
     * Save an article into an entity
     */
    public Article save(ArticleAddRequest request) {
        return blogRepository.save(request.toEntity());
    }

    /***
     * Find all articles
     */
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    /***
     * Find an article by its unique ID
     */
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
    }

    /***
     * Delete an article
     */
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    /***
     * Update an article
     */
    @Transactional
    public Article update(long id, ArticleUpdateRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
        article.update(request.getAuthor(), request.getTitle(), request.getContent());
        return article;
    }

}
