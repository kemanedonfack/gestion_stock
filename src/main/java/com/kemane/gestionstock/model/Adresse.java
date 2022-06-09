package com.kemane.gestionstock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "adresse1")
	private String adresse1;

	@Column(name = "adresse2")
	private String adresse2;

	@Column(name = "ville")
	private String city;

	@Column(name = "code_postal")
	private String codePostal;

	@Column(name = "pays")
	private String country;
	
	
}
