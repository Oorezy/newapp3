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
@RequestMapping("/blogposts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BlogPost blogPost = blogPostService.findById(id);
        model.addAttribute("blogPost", blogPost);
        return "blogposts/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBlogPost(@PathVariable Long id, @Valid @ModelAttribute("blogPost") BlogPost blogPost,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "blogposts/edit";
        }
        blogPostService.updateBlogPost(id, blogPost);
        return "redirect:/blogposts";
    }
}