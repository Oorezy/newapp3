package tech.justjava.simpleblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.justjava.simpleblog.entity.BlogPost;
import tech.justjava.simpleblog.repository.BlogPostRepository;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost findById(Long id) {
        return blogPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog post not found"));
    }

    public void updateBlogPost(Long id, BlogPost updatedBlogPost) {
        BlogPost existingBlogPost = findById(id);
        existingBlogPost.setTitle(updatedBlogPost.getTitle());
        existingBlogPost.setContent(updatedBlogPost.getContent());
        existingBlogPost.setCategories(updatedBlogPost.getCategories());
        blogPostRepository.save(existingBlogPost);
    }
}