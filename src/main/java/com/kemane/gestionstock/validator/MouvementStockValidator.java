package com.kemane.gestionstock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kemane.gestionstock.dto.MouvementStockDto;
import org.springframework.util.StringUtils;

public class MouvementStockValidator {

  public static List<String> validate(MouvementStockDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
      errors.add("Veuillez renseigner la quantite du mouvenent");
      errors.add("Veuillez renseigner l'article");
      errors.add("Veuillez renseigner le type du mouvement");

      return errors;
    }
    if (dto.getDateMouvement() == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
    }
    if (dto.getQuantity() == null || dto.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
      errors.add("Veuillez renseigner la quantite du mouvenent");
    }
    if (!StringUtils.hasLength(dto.getTypeMouvementStock().name())) {
      errors.add("Veuillez renseigner le type du mouvement");
    }

    return errors;
  }

}
