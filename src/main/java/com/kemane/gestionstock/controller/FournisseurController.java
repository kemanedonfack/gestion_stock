package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.CommandeFournisseurDto;
import com.kemane.gestionstock.dto.FournisseurDto;
import com.kemane.gestionstock.model.Fournisseur;
import com.kemane.gestionstock.service.FournisseurService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.kemane.gestionstock.utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(FOURNISSEUR_ENDPOINT)
@RestController
public class FournisseurController  {

    @Autowired
    private FournisseurService fournisseurService;

    @PostMapping( FOURNISSEUR_ENDPOINT+"/create")
    public ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto){
        return ResponseEntity.ok(fournisseurService.save(fournisseurDto));
    }

    @GetMapping(FOURNISSEUR_ENDPOINT+"/{idFournisseur}")
    public ResponseEntity<FournisseurDto> findById(@PathVariable Integer idFournisseur){
        return ResponseEntity.ok(fournisseurService.findById(idFournisseur));
    }

    @GetMapping(FOURNISSEUR_ENDPOINT+"/all")
    public ResponseEntity<List<FournisseurDto>>  findAll(){
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @DeleteMapping(value = FOURNISSEUR_ENDPOINT+"/delete/{idFournisseur}")
    public ResponseEntity delete(@PathVariable Integer idFournisseur){
        fournisseurService.delete(idFournisseur);
        return ResponseEntity.ok().build();
    }

}
