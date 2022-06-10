package com.kemane.gestionstock.repository;

import java.util.List;
import java.util.Optional;

import com.kemane.gestionstock.model.CommandeClient;
import com.kemane.gestionstock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<CommandeClient> findAllByFournisseurId(Integer id);
}
