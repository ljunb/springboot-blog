package com.example.iblog;

import com.example.iblog.domain.Article;
import com.example.iblog.services.impl.ArticleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IblogApplicationTests {

    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getArticleList() {
        List<Article> articleList = articleService.getArticleList();
        System.out.println("get article list >> \n " + articleList);
    }

    @Test
    public void createArticle() {
        Article article = new Article();
        article.setTitle("article 9");
        article.setDescription("desc 9");
        article.setAuthor("ljunb");
        int status = articleService.createArticle(article);
        System.out.println("create article status >> " + status);
    }

    @Test
    public void modifyArticle() {
        Article article = new Article();
        article.setArticleId(BigInteger.ONE);
        article.setTitle("article 1 new title");
        article.setDescription("article 1 new desc");
        article.setAuthor("article 1 new author");
        int status = articleService.modifyArticle(article);
        System.out.println("modify article status >> " + status);
    }

    @Test
    public void getArticle() {
        Article article = articleService.getArticle(BigInteger.ONE);
        System.out.println("get article >> " + article);
    }

    @Test
    public void deleteArticle() {
        int status = articleService.removeArticleById(BigInteger.ONE);
        System.out.println("delete article status >> " + status);
    }

}

