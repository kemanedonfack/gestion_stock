package com.kemane.gestionstock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntrepriseDto {

	private int id;
	
	private String name;

	private String description;

	private String codeFiscal;

	private AdresseDto adresse;
	
	private String photo;

	private String email;

	private String phoneNumber;

	private String webSite;

	@JsonIgnore
	private List<UtilisateurDto> utilisateurs;

	public static EntrepriseDto fromEntity(Entreprise entreprise) {
		if(entreprise == null) {
			return null;
		}
		return EntrepriseDto.builder()
				.id(entreprise.getId())
				.name(entreprise.getName())
				.email(entreprise.getEmail())
				.description(entreprise.getDescription())
				.codeFiscal(entreprise.getCodeFiscal())
				.adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
				.phoneNumber(entreprise.getPhoneNumber())
				.photo(entreprise.getPhoto())
				.webSite(entreprise.getWebSite())
				.build();
	}

	public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
		if(entrepriseDto == null) {
			return null;
		}
		Entreprise entreprise = new Entreprise();
		entreprise.setId(entrepriseDto.getId());
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setEmail(entrepriseDto.getEmail());
		entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));
		entreprise.setPhoneNumber(entrepriseDto.getPhoneNumber());
		entreprise.setWebSite(entrepriseDto.getWebSite());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setName(entrepriseDto.getName());

		return  entreprise;
	}
	
}
