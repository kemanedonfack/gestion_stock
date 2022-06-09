package com.kemane.gestionstock.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mouvement_stock")
public class MouvementStock extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "date_mouvement")
	private Instant dateMouvement;
	
	@Column(name = "quantite")
	private BigDecimal quantity;
	
	@ManyToOne
	@JoinColumn(name = "idarticle")
	private Article article;
	
	@Column(name = "type_mouvement_stock")
	private TypeMouvementStock mouvementStock;
}	

