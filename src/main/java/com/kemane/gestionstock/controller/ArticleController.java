package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ArticleDto;
import com.kemane.gestionstock.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/articles")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un nouvel artcile ", notes = "(Ajouter / Modifier)", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Objet crée ou modifié")
    })
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        return articleService.save(articleDto);
    }

    @GetMapping(value = "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un artcile ", notes = "Cette méthode permet de chercher un artcile grâce à son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été trouvé dans la base de données"),
            @ApiResponse(code = 404, message = "Aucun article avec cette ID")
    })
    public ArticleDto findById(@PathVariable Integer idArticle){
        return articleService.findById(idArticle);
    }

    @GetMapping(value = "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un artcile ", notes = "Cette méthode permet de chercher un artcile grâce à son code", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été trouvé dans la base de données"),
            @ApiResponse(code = 404, message = "Aucun article avec ce code")
    })
    public ArticleDto findByCodeArticle(@PathVariable String codeArticle){
       return articleService.findByCodeArticle(codeArticle);
    }

    @GetMapping(value = "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Récuperer tous les articles ", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été trouvé dans la base de données"),
    })
    public List<ArticleDto> findAll(){
        return articleService.findAll();
    }

    @DeleteMapping(value = "/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un article ", notes = "Cette méthode permet de supprimer un artcile dans la bdd par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été supprimé de la base de données")
    })
    public void delete(@PathVariable Integer idArticle){
        articleService.delete(idArticle);
    }


}
