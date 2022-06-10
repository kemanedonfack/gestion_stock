package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ArticleDto;
import com.kemane.gestionstock.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        return articleService.save(articleDto);
    }

    @GetMapping(value = "/article/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findById(@PathVariable Integer idArticle){
        return articleService.findById(idArticle);
    }

    @GetMapping(value = "/article/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findByCodeArticle(@PathVariable String codeArticle){
       return articleService.findByCodeArticle(codeArticle);
    }

    @GetMapping(value = "/article/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> findAll(){
        return articleService.findAll();
    }

    @DeleteMapping(value = "/article/delete/{idArticle}")
    public void delete(@PathVariable Integer idArticle){
        articleService.delete(idArticle);
    }


}
