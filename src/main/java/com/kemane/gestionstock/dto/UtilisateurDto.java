package com.kemane.gestionstock.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

	private Instant dateDeNaissance;

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
				.surname(utilisateur.getSurname())
				.email(utilisateur.getEmail())
				.password(utilisateur.getPassword())
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
				.photo(utilisateur.getPhoto())
				.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
				.roles(
						utilisateur.getRoles() != null ?
								utilisateur.getRoles().stream()
										.map(RoleDto::fromEntity)
										.collect(Collectors.toList()) : null
				)
				.build();
	}

	public static Utilisateur toEntity(UtilisateurDto dto) {
		if(dto == null) {
			return null;
		}
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(dto.getId());
		utilisateur.setName(dto.getName());
		utilisateur.setSurname(dto.getSurname());
		utilisateur.setEmail(dto.getEmail());
		utilisateur.setPassword(dto.getPassword());
		utilisateur.setDateDeNaissance(dto.getDateDeNaissance());
		utilisateur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
		utilisateur.setPhoto(dto.getPhoto());
		utilisateur.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));

		return  utilisateur;
	}
	
}
