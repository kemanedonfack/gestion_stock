package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.CategoryDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @Test
    public void testSaveCategory(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("category test")
                .designation("designation test")
                .idEntreprise(1)
                .build();

        CategoryDto saveCategory = service.save(expectedCategory);

        assertNotNull(saveCategory);
        assertNotNull(saveCategory.getId());
        assertEquals(expectedCategory.getCode(), saveCategory.getCode());
        assertEquals(expectedCategory.getDesignation(), saveCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(), saveCategory.getIdEntreprise());
    }

    @Test
    public void testUpdateCategory() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat update");

        savedCategory = service.save(categoryToUpdate);

        assertNotNull(categoryToUpdate);
        assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void testEntityInvalid() {
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCode());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("Veuillez renseignez le code de la catégorie", expectedException.getErrors().get(0));
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, expectedException.getErrorCode());
        assertEquals("Aucune category avec l'ID = 0 n' ete trouve dans la BDD", expectedException.getMessage());
    }




}