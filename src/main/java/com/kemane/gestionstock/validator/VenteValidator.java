package com.kemane.gestionstock.validator;

import java.util.ArrayList;
import java.util.List;

import com.kemane.gestionstock.dto.VenteDto;
import org.springframework.util.StringUtils;

public class VenteValidator {

  public static List<String> validate(VenteDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner le code de la commande");
      errors.add("Veuillez renseigner la date de la commande");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getCode())) {
      errors.add("Veuillez renseigner le code de la commande");
    }
    if (dto.getDateVente() == null) {
      errors.add("Veuillez renseigner la date de la commande");
    }

    return errors;
  }

}
