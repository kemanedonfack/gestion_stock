package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.CommandeClientDto;
import com.kemane.gestionstock.dto.CommandeFournisseurDto;
import com.kemane.gestionstock.service.CommandeFournisseurService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.APP_ROOT;
import static com.kemane.gestionstock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
@RestController
public class CommandeFournisseurController {

    @Autowired
    private CommandeFournisseurService commandeFournisseurService;

    @PostMapping( COMMANDE_FOURNISSEUR_ENDPOINT+"/create")
    public ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto){
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDto));
    }

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/{idCommandeFournisseur}")
    public ResponseEntity<CommandeFournisseurDto> findById(@PathVariable Integer idCommandeFournisseur){
        return ResponseEntity.ok(commandeFournisseurService.findById(idCommandeFournisseur));
    }

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/all")
    public ResponseEntity<List<CommandeFournisseurDto>>  findAll(){
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT+"/delete/{idCommandeFournisseur}")
    public ResponseEntity delete(@PathVariable Integer idCommandeFournisseur){
        commandeFournisseurService.delete(idCommandeFournisseur);

        return ResponseEntity.ok().build();
    }

}
