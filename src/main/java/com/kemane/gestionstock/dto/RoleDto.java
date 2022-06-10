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

	public static Role toEntity(RoleDto roleDto) {
		if(roleDto == null) {
			return null;
		}
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setRoleName(roleDto.getRoleName());

		return  role;
	}
}
