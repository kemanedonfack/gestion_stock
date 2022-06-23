package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.FournisseurDto;
import com.kemane.gestionstock.dto.UtilisateurDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Fournisseur;
import com.kemane.gestionstock.model.Utilisateur;
import com.kemane.gestionstock.repository.FournisseurRepository;
import com.kemane.gestionstock.repository.UtilisateurRepository;
import com.kemane.gestionstock.validator.FournisseurValidator;
import com.kemane.gestionstock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurDto save(UtilisateurDto utilisateurDto){
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()){
            log.error("Utilisateur is not valid", utilisateurDto);
            throw new InvalidEntityException("Utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        utilisateurDto.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        Utilisateur saveUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto));

        return UtilisateurDto.fromEntity(saveUtilisateur);
    }

    public UtilisateurDto findById(Integer id){
        if (id == null){
            log.error("Utilisateur ID is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(utilisateurDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID "+id+" trouvé", ErrorCodes.UTILISATEUR_NOT_FOUND
                )
        );
    }

    public List<UtilisateurDto> findAll(){
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }

    public UtilisateurDto findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }
}
