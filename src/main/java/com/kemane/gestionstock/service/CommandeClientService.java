package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.ArticleDto;
import com.kemane.gestionstock.dto.CommandeClientDto;
import com.kemane.gestionstock.dto.LigneCommandeClientDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Article;
import com.kemane.gestionstock.model.Client;
import com.kemane.gestionstock.model.CommandeClient;
import com.kemane.gestionstock.model.LigneCommandeClient;
import com.kemane.gestionstock.repository.ArticleRepository;
import com.kemane.gestionstock.repository.ClientRepository;
import com.kemane.gestionstock.repository.CommandeClientRepository;
import com.kemane.gestionstock.repository.LigneCommandeClientRepository;
import com.kemane.gestionstock.validator.ArticleValidator;
import com.kemane.gestionstock.validator.CommandeClientValidator;
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
public class CommandeClientService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommandeClientRepository commandeClientRepository;

    @Autowired
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    public CommandeClientDto save(CommandeClientDto commandeClientDto){
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()){
            log.error("Commande client is not valid", commandeClientDto);
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (!client.isEmpty()){
            log.warn("Client with ID "+commandeClientDto.getClient().getId()+" not found");
            throw new EntityNotFoundException("Client introuvable",ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClient() != null ){
            commandeClientDto.getLigneCommandeClient().forEach(
                    ligneCommandeClientDto -> {
                        if(ligneCommandeClientDto.getArticle() != null){
                            Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getArticle().getId());
                            if (article.isEmpty()){
                                articleErrors.add("L' article avec id "+ligneCommandeClientDto.getArticle().getId()+" n'existe pas ");
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

        CommandeClient saveCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if (commandeClientDto.getLigneCommandeClient() != null){
            commandeClientDto.getLigneCommandeClient().forEach(
                    ligneCommandeClientDto -> {
                        LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommandeClientDto);
                        ligneCommandeClient.setCommandeClient(saveCommandeClient);
                        ligneCommandeClientRepository.save(ligneCommandeClient);
                    }
            );
        }

        return CommandeClientDto.fromEntity(saveCommandeClient);
    }

    public CommandeClientDto findById(Integer id){
        if (id == null){
            log.error("CommandeClient ID is null");
            return null;
        }

        Optional<CommandeClient> commandeClient = commandeClientRepository.findById(id);
        CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient.get());

        return Optional.of(commandeClientDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande client avec l'ID "+id+" trouvé", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                )
        );
    }

    public CommandeClientDto findByCode(String code){

        if (!StringUtils.hasLength(code)){
            log.error("CommandeClientDto CODE is null");
            return null;
        }

        Optional<CommandeClient> commandeClient = commandeClientRepository.findCommandeClientByCode(code);
        CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient.get());

        return Optional.of(commandeClientDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commandeClientDto avec le code "+code+" trouvé", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                )
        );

    }

    public List<CommandeClientDto> findAll(){
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
