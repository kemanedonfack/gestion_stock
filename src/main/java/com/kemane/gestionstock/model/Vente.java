package com.kemane.gestionstock.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventes")
public class Vente extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "date_vente")
	private Instant dateVente;
	
	@Column(name = "commentaire")
	private String commentaire;
}
