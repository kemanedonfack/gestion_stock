package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ArticleDto;
import com.kemane.gestionstock.dto.CategoryDto;
import com.kemane.gestionstock.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT+"/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = APP_ROOT+"/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une nouvelle categorie ", notes = "(Ajouter / Modifier)", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Objet crée ou modifié")
    })
    public CategoryDto save(@RequestBody CategoryDto categoryDto){
        return categoryService.save(categoryDto);
    }

    @GetMapping(value = APP_ROOT+"/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une catégorie ", notes = "Cette méthode permet de chercher une catégorie grâce à son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été trouvé dans la base de données"),
            @ApiResponse(code = 404, message = "Aucun article avec cette ID")
    })
    public CategoryDto findById(@PathVariable Integer idCategory){
        return categoryService.findById(idCategory);
    }

    @GetMapping(value = APP_ROOT+"/categories/{code}/code", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une catégorie ", notes = "Cette méthode permet de chercher un catégorie grâce à son code", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie à été trouvé dans la base de données"),
            @ApiResponse(code = 404, message = "Aucune catégorie avec ce code")
    })
    public CategoryDto findByCodeCategorie(@PathVariable String code){
        return categoryService.findByCode(code);
    }

    @GetMapping(value = APP_ROOT+"/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Récuperer tous les catégories ", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listes des catégories de la base de données"),
    })
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une catégorie ", notes = "Cette méthode permet de supprimer une catégorie dans la bdd par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La catégorie à été supprimé de la base de données")
    })
    public void delete(@PathVariable Integer idCategory){
        categoryService.delete(idCategory);
    }
}
