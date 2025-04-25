package com.example.userdemo.service.impl;

import com.example.userdemo.model.User;
import com.example.userdemo.repository.LdapRepository;
import com.example.userdemo.service.UserFetchStrategy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LdapUserStrategy implements UserFetchStrategy {

    private final LdapRepository ldapRepository;

    public LdapUserStrategy(LdapRepository ldapRepository)
    {
        this.ldapRepository = ldapRepository;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return ldapRepository.getLDAPUsersById(id).map(ldapUser -> new User(ldapUser.getCatercId(), ldapUser.getEmail(), ldapUser.getGivenName()));
    }
}
