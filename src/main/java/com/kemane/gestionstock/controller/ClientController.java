package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT+"/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = APP_ROOT+"/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une nouvelle client ", notes = "(Ajouter / Modifier)", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Objet crée ou modifié")
    })
    public ClientDto save(@RequestBody ClientDto clientDto){
        return clientService.save(clientDto);
    }

    @GetMapping(value = APP_ROOT+"/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une client ", notes = "Cette méthode permet de chercher un client grâce à son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client à été trouvé dans la base de données"),
            @ApiResponse(code = 404, message = "Aucun client avec cette ID")
    })
    public ClientDto findById(@PathVariable Integer idClient){
        return clientService.findById(idClient);
    }


    @GetMapping(value = APP_ROOT+"/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Récuperer tous les clients ", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listes des clients de la base de données"),
    })
    public List<ClientDto> findAll(){
        return clientService.findAll();
    }

    @DeleteMapping(value = APP_ROOT+"/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un client ", notes = "Cette méthode permet de supprimer un client dans la bdd par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client à été supprimé de la base de données")
    })
    public void delete(@PathVariable Integer idClient){
        clientService.delete(idClient);
    }
}
