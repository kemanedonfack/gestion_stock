package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.dto.UtilisateurDto;
import com.kemane.gestionstock.service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.*;
import static com.kemane.gestionstock.utils.Constants.APP_ROOT;

@Api(UTILISATEUR_ENDPOINT)
@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping(UTILISATEUR_ENDPOINT+"/create")
    public UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.save(utilisateurDto);
    }

    @GetMapping(UTILISATEUR_ENDPOINT+"/{idUtilisateur}")
    public UtilisateurDto findById(@PathVariable Integer idUtilisateur){
        return utilisateurService.findById(idUtilisateur);
    }

    @GetMapping(UTILISATEUR_ENDPOINT+"/all")
    public List<UtilisateurDto> findAll(){
        return utilisateurService.findAll();
    }

    @DeleteMapping(UTILISATEUR_ENDPOINT+"/delete/{idUtilisateur}")
    public void delete(@PathVariable Integer idUtilisateur){
        utilisateurService.delete(idUtilisateur);
    }
}
