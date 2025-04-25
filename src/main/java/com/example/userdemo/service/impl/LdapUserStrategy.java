package com.example.userdemo.service.impl;

import com.example.userdemo.exception.LdapUserNotFoundException;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.LdapRepository;
import com.example.userdemo.service.UserFetchStrategy;


import java.util.Optional;

public class LdapUserStrategy implements UserFetchStrategy {

    private final LdapRepository ldapRepository;

    public LdapUserStrategy(LdapRepository ldapRepository)
    {
        this.ldapRepository = ldapRepository;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(ldapRepository.getLDAPUsersById(id)
                .map(ldapUser -> new User(ldapUser.getCatercId(), ldapUser.getEmail(), ldapUser.getGivenName()))
                .orElseThrow(() -> new LdapUserNotFoundException("LDAP user not found with id: " + id)));
    }
}
