package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.CategoryDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Category;
import com.kemane.gestionstock.repository.CategoryRepository;
import com.kemane.gestionstock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto save(CategoryDto categoryDto){
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()){
            log.error("Category is not valid", categoryDto);
            throw new InvalidEntityException("L'category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Category saveCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));

        return CategoryDto.fromEntity(saveCategory);
    }

    public CategoryDto findById(Integer id){
        if (id == null){
            log.error("Category ID is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(id);
        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Category avec l'ID "+id+" trouvé", ErrorCodes.CATEGORY_NOT_FOUND
                )
        );
    }

    public CategoryDto findByCode(String code){

        if (!StringUtils.hasLength(code)){
            log.error("Category CODE is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findCategoryByCode(code);
        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le code "+code+" trouvé", ErrorCodes.CATEGORY_NOT_FOUND
                )
        );

    }

    public List<CategoryDto> findAll(){
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        categoryRepository.deleteById(id);
    }

}
