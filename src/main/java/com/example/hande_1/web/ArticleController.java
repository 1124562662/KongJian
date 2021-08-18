package com.example.hande_1.web;

import com.example.hande_1.Data.Article;
import com.example.hande_1.dao.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping
    String aget(Model model){
        Iterable<Article> showAricles = this.articleRepository.GetLatest(Article.ContentType.Article,10);
        for(Article a:showAricles){
            model.addAttribute(a.getTitle(),a);
        }
        return "article";
    }
}
