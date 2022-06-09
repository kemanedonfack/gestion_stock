package com.kemane.gestionstock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fournisseurs")
public class Fournisseur extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nom")
	private String name;

	@Column(name = "prenom")
	private String surname;
	
	@Embedded
	private Adresse adresse;

	@Column(name = "photo")
	private String photo;

	@Column(name = "email")
	private String mail;

	@Column(name = "numero")
	private String phoneNumber;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<CommandeFournisseur> commandeFournisseurs;
	
}
