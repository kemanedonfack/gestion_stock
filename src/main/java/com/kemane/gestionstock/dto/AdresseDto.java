package com.kemane.gestionstock.dto;

import com.kemane.gestionstock.model.Adresse;
import com.kemane.gestionstock.model.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdresseDto {
	
	private String adresse1;

	private String adresse2;

	private String city;

	private String codePostal;

	private String country;

	public static AdresseDto fromEntity(Adresse adresse) {
		if(adresse == null) {
			return null;
		}
		return AdresseDto.builder()
				.adresse1(adresse.getAdresse1())
				.adresse2(adresse.getAdresse2())
				.city(adresse.getCity())
				.codePostal(adresse.getCodePostal())
				.country(adresse.getCountry())
				.build();
	}

	public static Adresse toEntity(AdresseDto adresseDto) {

		if(adresseDto == null) {
			return null;
		}
		Adresse adresse = new Adresse();
		adresse.setAdresse1(adresseDto.adresse1);
		adresse.setAdresse2(adresseDto.adresse2);
		adresse.setCity(adresseDto.getCity());
		adresse.setCountry(adresseDto.getCountry());
		adresse.setCodePostal(adresseDto.getCodePostal());

		return  adresse;
	}
	
}
