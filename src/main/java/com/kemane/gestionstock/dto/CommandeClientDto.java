package com.kemane.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeClientDto {

	private int id;

	private String code;

	private Instant dateCommande;

	private ClientDto client;

	private List<LigneCommandeClientDto> ligneCommandeClient;

	private Integer idEntreprise;

	public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		if(commandeClient == null) {
			return null;
		}
		return CommandeClientDto.builder()
				.id(commandeClient.getId())
				.code(commandeClient.getCode())
				.idEntreprise(commandeClient.getIdEntreprise())
				.dateCommande(commandeClient.getDateCommande())
				.client(ClientDto.fromEntity(commandeClient.getClient()))
				.build();
	}

	public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
		if(commandeClientDto == null) {
			return null;
		}
		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setId(commandeClientDto.getId());
		commandeClient.setCode(commandeClientDto.getCode());
		commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
		commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
		commandeClient.setDateCommande(commandeClientDto.getDateCommande());

		return  commandeClient;
	}
	
}
