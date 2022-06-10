package com.kemane.gestionstock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Fournisseur;
import com.kemane.gestionstock.model.Role;
import com.kemane.gestionstock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilisateurDto {

	private int id;
	
	private String name;
	
	private String surname;

	private String email;

	private String dateDeNaissance;

	private String password;

	private AdresseDto adresse;

	private String photo;

	private EntrepriseDto entreprise;

	@JsonIgnore
	private List<RoleDto> roles;

	public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
		if(utilisateur == null) {
			return null;
		}
		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.name(utilisateur.getName())
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.password(utilisateur.getPassword())
				.surname(utilisateur.getSurname())
				.photo(utilisateur.getPhoto())
				.email(utilisateur.getEmail())
				.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
				.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
				.build();
	}

	public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
		if(utilisateurDto == null) {
			return null;
		}
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setName(utilisateurDto.getName());
		utilisateur.setPhoto(utilisateurDto.getPhoto());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setSurname(utilisateurDto.getSurname());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		utilisateur.setPassword(utilisateurDto.getPassword());
		utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));

		return  utilisateur;
	}
	
}
