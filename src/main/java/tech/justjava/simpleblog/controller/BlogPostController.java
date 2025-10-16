package tech.justjava.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.justjava.simpleblog.entity.BlogPost;
import tech.justjava.simpleblog.service.BlogPostService;

import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        model.addAttribute("blogPost", blogPost);
        return "blog/delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/blog";
    }
}
