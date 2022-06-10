package com.kemane.gestionstock.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeClientDto {

	private int id;

	private ArticleDto article;

	@JsonIgnore
	private CommandeClientDto commandeClient;
	
	private BigDecimal quantity;

	private BigDecimal prixUnitaire;

	public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
		if(ligneCommandeClient == null) {
			return null;
		}
		return LigneCommandeClientDto.builder()
				.id(ligneCommandeClient.getId())
				.article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
				.quantity(ligneCommandeClient.getQuantity())
				.prixUnitaire(ligneCommandeClient.getPrixUnitaire())
				.build();
	}

	public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
		if(ligneCommandeClientDto == null) {
			return null;
		}
		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
		ligneCommandeClient.setId(ligneCommandeClientDto.getId());
		ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
		ligneCommandeClient.setQuantity(ligneCommandeClientDto.getQuantity());
		ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());

		return  ligneCommandeClient;
	}
	
}
