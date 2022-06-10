package com.kemane.gestionstock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.Client;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ClientDto {

	private int id;
	
	private String name;

	private String surname;

	private AdresseDto adresse;

	private String photo;

	private String mail;

	private String number;

	@JsonIgnore
	private List<CommandeClientDto> commandeClients;

	public static ClientDto fromEntity(Client client) {
		if(client == null) {
			return null;
		}
		return ClientDto.builder()
				.id(client.getId())
				.name(client.getName())
				.surname(client.getSurname())
				.photo(client.getPhoto())
				.mail(client.getMail())
				.number(client.getNumber())
				.adresse(AdresseDto.fromEntity(client.getAdresse()))
				.build();
	}

	public static Client toEntity(ClientDto clientDto) {
		if(clientDto == null) {
			return null;
		}
		Client client = new Client();
		client.setId(clientDto.getId());
		client.setMail(clientDto.getMail());
		client.setName(clientDto.getName());
		client.setSurname(clientDto.getSurname());
		client.setNumber(clientDto.getNumber());
		client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
		client.setPhoto(clientDto.getPhoto());

		return  client;
	}
}
