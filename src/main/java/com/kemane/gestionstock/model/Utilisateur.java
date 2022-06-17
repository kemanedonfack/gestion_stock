package com.kemane.gestionstock.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "utilisateurs")
public class Utilisateur extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nom")
	private String name;
	
	@Column(name = "prenom")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "date_naissance")
	private Instant dateDeNaissance;
	
	@Column(name = "mot_de_passe")
	private String password;
	
	@Embedded
	private Adresse adresse;
	
	@Column(name = "photo")
	private String photo;

	@ManyToOne
	@JoinColumn(name = "identreprise")
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Role> roles;
	
	
}
