package com.inje.lublio.blog.repository;

import com.inje.lublio.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
