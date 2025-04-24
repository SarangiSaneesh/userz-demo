package com.example.userdemo.service;

import com.example.userdemo.model.LDAPUser;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LdapSearchService ldapSearchService;
    private final boolean isLdapEnabled;

    public UserService(UserRepository userRepository, LdapSearchService ldapSearchService, @Value("${app.ldap.enabled}") boolean isLdapEnabled) {
        this.userRepository = userRepository;
        this.ldapSearchService = ldapSearchService;
        this.isLdapEnabled = isLdapEnabled;
    }

    public Optional<User> getUserById(String id) {
        if (isLdapEnabled) {
            return ldapSearchService.getLDAPUsersById(id).map(this::mapLdapToUser);
        } else {
            return userRepository.getUsersById(id);
        }
    }

    private User mapLdapToUser(LDAPUser ldapUser) {
        return new User(ldapUser.getCatercId(), ldapUser.getEmail(), ldapUser.getGivenName());
    }



}
