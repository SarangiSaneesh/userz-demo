package com.example.userdemo.service;

import com.example.userdemo.model.LDAPUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LdapSearchService {
    private static final Map<String, LDAPUser> ldapStore = new HashMap<>();

    static {
        ldapStore.put("1", new LDAPUser("101", "ldapuser1@example.com", "LdapJohn"));
        ldapStore.put("2", new LDAPUser("102", "ldapuser2@example.com", "LdapJane"));
    }

    public Optional<LDAPUser> getLDAPUsersById(String id) {
        return Optional.ofNullable(ldapStore.get(id));
    }
}
