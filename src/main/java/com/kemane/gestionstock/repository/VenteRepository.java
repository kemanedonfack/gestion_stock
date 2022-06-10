package com.kemane.gestionstock.repository;


import com.kemane.gestionstock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente, Integer> {

  Optional<Vente> findVentesByCode(String code);
}
