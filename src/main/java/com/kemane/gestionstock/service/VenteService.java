package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.VenteDto;
import com.kemane.gestionstock.dto.LigneCommandeClientDto;
import com.kemane.gestionstock.dto.LigneVenteDto;
import com.kemane.gestionstock.dto.VenteDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.*;
import com.kemane.gestionstock.repository.*;
import com.kemane.gestionstock.validator.CommandeClientValidator;
import com.kemane.gestionstock.validator.VenteValidator;
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
public class VenteService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private LigneVenteRepository ligneVenteRepository;

    public VenteDto save(VenteDto venteDto){
        List<String> errors = VenteValidator.validate(venteDto);
        if (!errors.isEmpty()){
            log.error("Vente is not valid", venteDto);
            throw new InvalidEntityException("La vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        venteDto.getLigneVentes().forEach(
                ligneVenteDto -> {
                    Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L' article avec id "+ligneVenteDto.getArticle().getId()+" n'existe pas ");
                    }
                }
            );

        if (!articleErrors.isEmpty()){
            log.warn("one or more article not found in db "+errors);
            throw new InvalidEntityException("un ou plusieurs article introuvable",ErrorCodes.VENTE_NOT_VALID, articleErrors);
        }

        Vente saveVente = venteRepository.save(VenteDto.toEntity(venteDto));

        if (venteDto.getLigneVentes() != null){
            venteDto.getLigneVentes().forEach(
                    ligneVenteDto -> {
                        LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
                        ligneVente.setVente(saveVente);
                        ligneVenteRepository.save(ligneVente);
                    }
            );
        }

        return VenteDto.fromEntity(saveVente);
    }

    public VenteDto findById(Integer id){
        if (id == null){
            log.error("VenteDto ID is null");
            return null;
        }

        Optional<Vente> vente = venteRepository.findById(id);
        VenteDto venteDto = VenteDto.fromEntity(vente.get());

        return Optional.of(venteDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune vente avec l'ID "+id+" trouvé", ErrorCodes.VENTE_NOT_FOUND
                )
        );
    }

    public VenteDto findByCode(String code){

        if (!StringUtils.hasLength(code)){
            log.error("vente CODE is null");
            return null;
        }

        Optional<Vente> vente = venteRepository.findVentesByCode(code);
        VenteDto venteDto = VenteDto.fromEntity(vente.get());

        return Optional.of(venteDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune vente avec le code "+code+" trouvé", ErrorCodes.VENTE_NOT_FOUND
                )
        );

    }

    public List<VenteDto> findAll(){
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        venteRepository.deleteById(id);
    }
}
