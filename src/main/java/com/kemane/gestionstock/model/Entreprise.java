package com.kemane.gestionstock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "entreprises")
public class Entreprise extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nom")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "code_fiscal")
	private String codeFiscal;
	
	@Embedded
	private Adresse adresse;
	
	@Column(name = "photo")
	private String photo;

	@Column(name = "email")
	private String email;
	
	@Column(name = "numero_telephone")
	private String phoneNumber;
	
	@Column(name = "site_web")
	private String webSite;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Utilisateur> utilisateurs;
	
	
}
