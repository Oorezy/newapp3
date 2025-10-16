package tech.justjava.simpleblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.justjava.simpleblog.entity.BlogPost;
import tech.justjava.simpleblog.service.BlogPostService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogPostControllerTest {

    @Mock
    private BlogPostService blogPostService;

    @InjectMocks
    private BlogPostController blogPostController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogPostController).build();
    }

    @Test
    public void testShowEditForm() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("Test Title");
        blogPost.setContent("Test Content");
        blogPost.setCategories("Test Categories");

        when(blogPostService.findById(anyLong())).thenReturn(blogPost);

        mockMvc.perform(get("/blogposts/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogposts/edit"))
                .andExpect(model().attributeExists("blogPost"));

        verify(blogPostService, times(1)).findById(anyLong());
    }

    @Test
    public void testUpdateBlogPost() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("Updated Title");
        blogPost.setContent("Updated Content");
        blogPost.setCategories("Updated Categories");

        doNothing().when(blogPostService).updateBlogPost(anyLong(), any(BlogPost.class));

        mockMvc.perform(post("/blogposts/edit/1")
                .param("title", "Updated Title")
                .param("content", "Updated Content")
                .param("categories", "Updated Categories"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blogposts"));

        verify(blogPostService, times(1)).updateBlogPost(anyLong(), any(BlogPost.class));
    }
}