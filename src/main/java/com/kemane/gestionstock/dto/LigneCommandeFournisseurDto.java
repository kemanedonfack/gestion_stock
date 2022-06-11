package com.kemane.gestionstock.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDto {

	private int id;

	private ArticleDto article;

	private CommandeFournisseurDto commandeFournisseur;

	private BigDecimal quantity;
	
	private BigDecimal prixUnitaire;

	private Integer idEntreprise;

	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		if(ligneCommandeFournisseur == null) {
			return null;
		}
		return LigneCommandeFournisseurDto.builder()
				.id(ligneCommandeFournisseur.getId())
				.article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
				.quantity(ligneCommandeFournisseur.getQuantity())
				.idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
				.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
				.build();
	}

	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
		if(ligneCommandeFournisseurDto == null) {
			return null;
		}
		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
		ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
		ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
		ligneCommandeFournisseur.setQuantity(ligneCommandeFournisseurDto.getQuantity());
		ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());

		return  ligneCommandeFournisseur;
	}
}
