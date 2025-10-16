package tech.justjava.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.justjava.simpleblog.service.SearchService;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchPosts(@RequestParam(required = false) String keyword,
                              @RequestParam(required = false) String category,
                              Model model) {
        model.addAttribute("posts", searchService.searchPosts(keyword, category));
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        return "search/results";
    }
}
