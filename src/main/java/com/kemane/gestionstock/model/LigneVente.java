package com.kemane.gestionstock.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ligne_vente")
public class LigneVente extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "identreprise")
	private Integer idEntreprise;

	@Column(name = "quantite")
	private BigDecimal quantity;

	@Column(name = "prix_unitaire")
	private BigDecimal prixUnitaire;

	@ManyToOne
	@JoinColumn(name = "idvente")
	private Vente vente;


	
}
