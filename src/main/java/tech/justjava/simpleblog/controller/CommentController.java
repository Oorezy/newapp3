package tech.justjava.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.justjava.simpleblog.entity.Comment;
import tech.justjava.simpleblog.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/blog/{blogId}")
    public String getCommentsByBlogId(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.getCommentsByBlogId(blogId);
        model.addAttribute("comments", comments);
        model.addAttribute("blogId", blogId);
        return "comment/list";
    }

    @GetMapping("/create/{blogId}")
    public String showCreateCommentForm(@PathVariable Long blogId, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("blogId", blogId);
        return "comment/create";
    }

    @PostMapping("/create/{blogId}")
    public String createComment(@PathVariable Long blogId, @ModelAttribute Comment comment) {
        commentService.createComment(blogId, comment);
        return "redirect:/comments/blog/" + blogId;
    }
}
