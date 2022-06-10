package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.dto.EntrepriseDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Client;
import com.kemane.gestionstock.model.Entreprise;
import com.kemane.gestionstock.repository.ClientRepository;
import com.kemane.gestionstock.repository.EntrepriseRepository;
import com.kemane.gestionstock.validator.ClientValidator;
import com.kemane.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public EntrepriseDto save(EntrepriseDto entrepriseDto){
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("Client is not valid", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        Entreprise saveEntreprise = entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto));

        return EntrepriseDto.fromEntity(saveEntreprise);
    }

    public EntrepriseDto findById(Integer id){
        if (id == null){
            log.error("Entreprise ID is null");
            return null;
        }

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(entrepriseDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'ID "+id+" trouvé", ErrorCodes.ENTREPRISE_NOT_FOUND
                )
        );
    }

    public List<EntrepriseDto> findAll(){
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
