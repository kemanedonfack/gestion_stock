package com.kemane.gestionstock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FournisseurDto {

	private int id;
	
	private String name;

	private String surname;

	private AdresseDto adresse;

	private String photo;

	private String mail;

	private String phoneNumber;

	@JsonIgnore
	private List<CommandeFournisseurDto> commandeFournisseurs;

	public static FournisseurDto fromEntity(Fournisseur fournisseur) {
		if(fournisseur == null) {
			return null;
		}
		return FournisseurDto.builder()
				.id(fournisseur.getId())
				.name(fournisseur.getName())
				.surname(fournisseur.getSurname())
				.photo(fournisseur.getPhoto())
				.mail(fournisseur.getMail())
				.adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
				.phoneNumber(fournisseur.getPhoneNumber())
				.build();
	}

	public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
		if(fournisseurDto == null) {
			return null;
		}
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(fournisseurDto.getId());
		fournisseur.setMail(fournisseurDto.getMail());
		fournisseur.setName(fournisseurDto.getName());
		fournisseur.setPhoto(fournisseurDto.getPhoto());
		fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));
		fournisseur.setPhoneNumber(fournisseurDto.getPhoneNumber());
		fournisseur.setSurname(fournisseurDto.getSurname());

		return  fournisseur;
	}
}	
