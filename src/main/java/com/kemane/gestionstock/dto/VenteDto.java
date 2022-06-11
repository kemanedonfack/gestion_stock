package com.kemane.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.kemane.gestionstock.model.LigneVente;
import com.kemane.gestionstock.model.Role;
import com.kemane.gestionstock.model.Vente;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class VenteDto {

	private int id;
	
	private String code;
	
	private Instant dateVente;
	
	private String commentaire;

	private Integer idEntreprise;

	private List<LigneVenteDto> ligneVentes;

	public static VenteDto fromEntity(Vente vente) {
		if(vente == null) {
			return null;
		}
		return VenteDto.builder()
				.id(vente.getId())
				.code(vente.getCode())
				.dateVente(vente.getDateVente())
				.idEntreprise(vente.getIdEntreprise())
				.commentaire(vente.getCommentaire())
				.build();
	}

	public static Vente toEntity(VenteDto venteDto) {
		if(venteDto == null) {
			return null;
		}
		Vente vente = new Vente();
		vente.setId(venteDto.getId());
		vente.setCode(venteDto.getCode());
		vente.setDateVente(venteDto.getDateVente());
		vente.setCommentaire(venteDto.getCommentaire());

		return  vente;
	}
	
}
