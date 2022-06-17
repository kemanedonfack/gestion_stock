package com.kemane.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.model.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDto {

	private int id;
	
	private String roleName;

	@JsonIgnore
	private UtilisateurDto utilisateur;

	public static RoleDto fromEntity(Role role) {
		if(role == null) {
			return null;
		}
		return RoleDto.builder()
				.id(role.getId())
				.roleName(role.getRoleName())
				.build();
	}

	public static Role toEntity(RoleDto dto) {
		if (dto == null) {
			return null;
		}
		Role roles = new Role();
		roles.setId(dto.getId());
		roles.setRoleName(dto.getRoleName());
		roles.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
		return roles;
	}
}
