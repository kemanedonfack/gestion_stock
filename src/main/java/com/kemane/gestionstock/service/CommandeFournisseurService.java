package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.CommandeFournisseurDto;
import com.kemane.gestionstock.dto.LigneCommandeFournisseurDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.*;
import com.kemane.gestionstock.repository.*;
import com.kemane.gestionstock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurService {

    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private FournisseurRepository fournisseurRepository;
    
    @Autowired
    private CommandeFournisseurRepository commandeFournisseurRepository;
    
    @Autowired
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);
        if (!errors.isEmpty()){
            log.error("Commande fournisseur is not valid", commandeFournisseurDto);
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (!fournisseur.isEmpty()){
            log.warn("Fournisseur with ID "+commandeFournisseurDto.getFournisseur().getId()+" not found");
            throw new EntityNotFoundException("Fournisseur introuvable",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null ){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(
                    ligneCommandeFournisseurDto -> {
                        if(ligneCommandeFournisseurDto.getArticle() != null){
                            Optional<Article> article = articleRepository.findById(ligneCommandeFournisseurDto.getArticle().getId());
                            if (article.isEmpty()){
                                articleErrors.add("L' article avec id "+ligneCommandeFournisseurDto.getArticle().getId()+" n'existe pas ");
                            }
                        }else{
                            articleErrors.add("Impossible d'enregistrer une commande avec un article NULL ");
                        }
                    }
            );
        }
        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas dans la base de donnée",ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur saveCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(
                    ligneCommandeFournisseurDto -> {
                        LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto);
                        ligneCommandeFournisseur.setCommandeFournisseur(saveCommandeFournisseur);
                        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
                    }
            );
        }

        return CommandeFournisseurDto.fromEntity(saveCommandeFournisseur);
    }

    public CommandeFournisseurDto findById(Integer id){
        if (id == null){
            log.error("CommandeFournisseur ID is null");
            return null;
        }

        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findById(id);
        CommandeFournisseurDto commandeFournisseurDto = CommandeFournisseurDto.fromEntity(commandeFournisseur.get());

        return Optional.of(commandeFournisseurDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande fournisseur avec l'ID "+id+" trouvé", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                )
        );
    }

    public CommandeFournisseurDto findByCode(String code){

        if (!StringUtils.hasLength(code)){
            log.error("Commande Fournisseur CODE is null");
            return null;
        }

        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findCommandeFournisseurByCode(code);
        CommandeFournisseurDto commandeFournisseurDto = CommandeFournisseurDto.fromEntity(commandeFournisseur.get());

        return Optional.of(commandeFournisseurDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande fournisseur avec le code "+code+" trouvé", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                )
        );

    }

    public List<CommandeFournisseurDto> findAll(){
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Commande fournisseur ID is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
