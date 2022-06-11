package com.kemane.gestionstock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemane.gestionstock.model.Category;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {
	
	private int id;
	
	private String code;

	private String designation;

	private Integer idEntreprise;

	@JsonIgnore
	private List<ArticleDto> articles;

	public static CategoryDto fromEntity(Category category) {
		if(category == null) {
			return null;
		}
		return CategoryDto.builder()
				.id(category.getId())
				.code(category.getCode())
				.idEntreprise(category.getIdEntreprise())
				.designation(category.getDesignation())
				.build();
	}

	public static Category toEntity(CategoryDto categoryDto) {
		if(categoryDto == null) {
			return null;
		}
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setCode(categoryDto.getCode());
		category.setIdEntreprise(categoryDto.getIdEntreprise());
		category.setDesignation(categoryDto.getDesignation());

		return  category;
	}
}
