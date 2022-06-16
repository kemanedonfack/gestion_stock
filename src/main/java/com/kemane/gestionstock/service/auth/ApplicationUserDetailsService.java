package com.kemane.gestionstock.service.auth;

import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.model.Utilisateur;
import com.kemane.gestionstock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(() ->
            new EntityNotFoundException("Aucun utilisateur trouvé", ErrorCodes.UTILISATEUR_NOT_FOUND)
        );

        return new User(utilisateur.getEmail(), utilisateur.getPassword(), Collections.emptyList());
    }
}
