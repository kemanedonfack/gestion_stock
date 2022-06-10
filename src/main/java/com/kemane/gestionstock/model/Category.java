package com.kemane.gestionstock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code")
	private String code;

	@Column(name = "designation")
	private String designation;

	@Column(name = "identreprise")
	private Integer idEntreprise;
	
	@OneToMany(mappedBy = "category")
	private List<Article> articles;
}
