package tech.justjava.simpleblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.justjava.simpleblog.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}