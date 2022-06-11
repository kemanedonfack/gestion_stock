package com.kemane.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeFournisseurDto {

	private int id;
	
	private String code;

	private Instant dateCommande;

	private FournisseurDto fournisseur;

	private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

	private Integer idEntreprise;

	public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		if(commandeFournisseur == null) {
			return null;
		}
		return CommandeFournisseurDto.builder()
				.id(commandeFournisseur.getId())
				.code(commandeFournisseur.getCode())
				.idEntreprise(commandeFournisseur.getIdEntreprise())
				.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
				.dateCommande(commandeFournisseur.getDateCommande())
				.build();
	}

	public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
		if(commandeFournisseurDto == null) {
			return null;
		}
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(commandeFournisseurDto.getId());
		commandeFournisseur.setCode(commandeFournisseurDto.getCode());
		commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));
		commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());

		return  commandeFournisseur;
	}
	
}
