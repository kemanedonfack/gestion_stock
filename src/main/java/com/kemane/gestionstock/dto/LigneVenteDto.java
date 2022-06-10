package com.kemane.gestionstock.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.LigneCommandeFournisseur;
import com.kemane.gestionstock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneVenteDto {

	private int id;

	private VenteDto vente;

	private ArticleDto article;
	
	private BigDecimal quantity;

	private BigDecimal prixUnitaire;

	public static LigneVenteDto fromEntity(LigneVente ligneVente) {
		if(ligneVente == null) {
			return null;
		}
		return LigneVenteDto.builder()
				.id(ligneVente.getId())
				.vente(VenteDto.fromEntity(ligneVente.getVente()))
				.quantity(ligneVente.getQuantity())
				.prixUnitaire(ligneVente.getPrixUnitaire())
				.build();
	}

	public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
		if(ligneVenteDto == null) {
			return null;
		}
		LigneVente ligneVente = new LigneVente();
		ligneVente.setId(ligneVenteDto.getId());
		ligneVente.setVente(VenteDto.toEntity(ligneVenteDto.getVente()));
		ligneVente.setQuantity(ligneVenteDto.getQuantity());
		ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());

		return  ligneVente;
	}
	
}
