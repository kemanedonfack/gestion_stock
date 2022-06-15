package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.dto.CommandeClientDto;
import com.kemane.gestionstock.service.CommandeClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kemane.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT+"/commandesclients")
@RestController
public class CommandeClientController {

    @Autowired
    private CommandeClientService commandeClientService;

    @PostMapping( APP_ROOT+"/commandesclients/create")
    public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto){
        return ResponseEntity.ok(commandeClientService.save(commandeClientDto));
    }

    @GetMapping(APP_ROOT+"/commandesclients/{idCommandeClient}")
    public ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient){
        return ResponseEntity.ok(commandeClientService.findById(idCommandeClient));
    }

    @GetMapping(APP_ROOT+"/commandesclients/all")
    public ResponseEntity<List<CommandeClientDto>>  findAll(){
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @DeleteMapping(value = APP_ROOT+"/commandesclients/delete/{idCommandeClient}")
    public ResponseEntity delete(@PathVariable Integer idCommandeClient){
        commandeClientService.delete(idCommandeClient);

        return ResponseEntity.ok().build();
    }


}
