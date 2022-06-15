package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.EntrepriseDto;
import com.kemane.gestionstock.dto.VenteDto;
import com.kemane.gestionstock.service.EntrepriseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.ENTREPRISE_ENDPOINT;

@Api(ENTREPRISE_ENDPOINT)
@RestController
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @PostMapping(ENTREPRISE_ENDPOINT+"/create")
    public EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto){
        return entrepriseService.save(entrepriseDto);
    }

    @GetMapping(ENTREPRISE_ENDPOINT+"/{idEntreprise}")
    public EntrepriseDto findById(@PathVariable Integer idEntreprise){
        return entrepriseService.findById(idEntreprise);
    }

    @GetMapping(ENTREPRISE_ENDPOINT+"/all")
    public List<EntrepriseDto> findAll(){
        return entrepriseService.findAll();
    }

    @DeleteMapping(ENTREPRISE_ENDPOINT+"/delete/{idEntreprise}")
    public void delete(@PathVariable Integer idEntreprise){
        entrepriseService.delete(idEntreprise);
    }
}
