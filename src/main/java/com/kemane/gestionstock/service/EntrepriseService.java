package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.dto.EntrepriseDto;
import com.kemane.gestionstock.dto.RoleDto;
import com.kemane.gestionstock.dto.UtilisateurDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Client;
import com.kemane.gestionstock.model.Entreprise;
import com.kemane.gestionstock.repository.ClientRepository;
import com.kemane.gestionstock.repository.EntrepriseRepository;
import com.kemane.gestionstock.repository.RolesRepository;
import com.kemane.gestionstock.validator.ClientValidator;
import com.kemane.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private RolesRepository rolesRepository;

    public EntrepriseDto save(EntrepriseDto entrepriseDto){
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );

        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateur);

        RoleDto rolesDto = RoleDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RoleDto.toEntity(rolesDto));

        return  savedEntreprise;
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

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .name(dto.getName())
                .surname(dto.getCodeFiscal())
                .email(dto.getEmail())
                .password(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

}
