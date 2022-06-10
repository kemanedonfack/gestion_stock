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
@Table(name = "articles")
public class Article extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code_article")
	private String codeArticle;

	@Column(name = "designation")
	private String designation;

	@Column(name = "prix_unitaire_ht")
	private BigDecimal prixUnitaireHt;

	@Column(name = "taux_tva")
	private BigDecimal tauxTva;

	@Column(name = "prix_unitaire_ttc")
	private BigDecimal prixUnitaireTtc;

	@Column(name = "photo")
	private String photo;

	@Column(name = "identreprise")
	private Integer idEntreprise;
	
	@ManyToOne
	@JoinColumn(name = "idcategorie")
	private Category category;
	
}
