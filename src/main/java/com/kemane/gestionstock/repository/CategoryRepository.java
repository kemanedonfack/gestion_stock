package com.kemane.gestionstock.repository;


import com.kemane.gestionstock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
