package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.EntrepriseDto;
import com.kemane.gestionstock.dto.FournisseurDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Entreprise;
import com.kemane.gestionstock.model.Fournisseur;
import com.kemane.gestionstock.repository.EntrepriseRepository;
import com.kemane.gestionstock.repository.FournisseurRepository;
import com.kemane.gestionstock.validator.EntrepriseValidator;
import com.kemane.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public FournisseurDto save(FournisseurDto fournisseurDto){
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid", fournisseurDto);
            throw new InvalidEntityException("Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        Fournisseur saveFournisseur = fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto));

        return FournisseurDto.fromEntity(saveFournisseur);
    }

    public FournisseurDto findById(Integer id){
        if (id == null){
            log.error("Fournisseur ID is null");
            return null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(fournisseurDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID "+id+" trouvé", ErrorCodes.FOURNISSEUR_NOT_FOUND
                )
        );
    }

    public List<FournisseurDto> findAll(){
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Fournisseur ID is null");
            return;
        }
        fournisseurRepository.deleteById(id);
    }
}
