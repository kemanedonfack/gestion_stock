package com.kemane.gestionstock.repository;

import java.util.List;

import com.kemane.gestionstock.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {

}
