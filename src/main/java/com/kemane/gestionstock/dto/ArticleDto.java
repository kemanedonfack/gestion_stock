package com.kemane.gestionstock.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Article;
import com.kemane.gestionstock.model.Category;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleDto {
	
	private int id;
	
	private String codeArticle;

	private String designation;

	private BigDecimal prixUnitaireHt;

	private BigDecimal tauxTva;

	private BigDecimal prixUnitaireTtc;

	private String photo;

	private CategoryDto category;

	private Integer idEntreprise;

	public static ArticleDto fromEntity(Article article) {
		if(article == null) {
			return null;
		}
		return ArticleDto.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.designation(article.getDesignation())
				.prixUnitaireHt(article.getPrixUnitaireHt())
				.prixUnitaireTtc(article.getPrixUnitaireTtc())
				.idEntreprise(article.getIdEntreprise())
				.tauxTva(article.getTauxTva())
				.category(CategoryDto.fromEntity(article.getCategory()))
				.photo(article.getPhoto())
				.build();
	}

	public static Article toEntity(ArticleDto articleDto) {
		if(articleDto == null) {
			return null;
		}
		Article article = new Article();
		article.setId(articleDto.getId());
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
		article.setTauxTva(articleDto.getTauxTva());
		article.setIdEntreprise(articleDto.getIdEntreprise());
		article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
		article.setPhoto(articleDto.getPhoto());

		return  article;
	}
	
}
