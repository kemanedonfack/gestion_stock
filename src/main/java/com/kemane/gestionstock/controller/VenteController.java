package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.UtilisateurDto;
import com.kemane.gestionstock.dto.VenteDto;
import com.kemane.gestionstock.service.VenteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.UTILISATEUR_ENDPOINT;
import static com.kemane.gestionstock.utils.Constants.VENTES_ENDPOINT;

@Api(VENTES_ENDPOINT)
@RestController
public class VenteController {

    @Autowired
    private VenteService venteService;

    @PostMapping(VENTES_ENDPOINT+"/create")
    public VenteDto save(@RequestBody VenteDto venteDto){
        return venteService.save(venteDto);
    }

    @GetMapping(VENTES_ENDPOINT+"/{idVente}")
    public VenteDto findById(@PathVariable Integer idVente){
        return venteService.findById(idVente);
    }

    @GetMapping(VENTES_ENDPOINT+"/all")
    public List<VenteDto> findAll(){
        return venteService.findAll();
    }

    @DeleteMapping(VENTES_ENDPOINT+"/delete/{idVente}")
    public void delete(@PathVariable Integer idVente){
        venteService.delete(idVente);
    }
}
