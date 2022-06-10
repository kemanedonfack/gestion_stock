package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.ClientDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Client;
import com.kemane.gestionstock.repository.ClientRepository;
import com.kemane.gestionstock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDto save(ClientDto clientDto){
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()){
            log.error("Client is not valid", clientDto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        Client saveClient = clientRepository.save(ClientDto.toEntity(clientDto));

        return ClientDto.fromEntity(saveClient);
    }

    public ClientDto findById(Integer id){
        if (id == null){
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);
        ClientDto clientDto = ClientDto.fromEntity(client.get());

        return Optional.of(clientDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune client avec l'ID "+id+" trouvé", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                )
        );
    }

    public List<ClientDto> findAll(){
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
