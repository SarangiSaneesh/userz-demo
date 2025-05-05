package com.example.userdemo.service.impl;

import com.example.userdemo.exception.LdapUserNotFoundException;
import com.example.userdemo.model.LDAPUser;
import com.example.userdemo.model.User;
import com.example.userdemo.repository.LdapRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class LdapUserStrategyTest {

    @Mock
    private LdapRepository ldapRepository;

    @InjectMocks
    private LdapUserStrategy ldapUserStrategy;

    public LdapUserStrategyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_sucess() {
        LDAPUser mockLdapUser = new LDAPUser("1","sanuldap@gmail.com","sanu");
        when(ldapRepository.getLDAPUsersById("1")).thenReturn(Optional.of(mockLdapUser));
        Optional<User> result = ldapUserStrategy.getUserById("1");

        assertTrue(result.isPresent());
        assertEquals("1",result.get().getId());
        assertEquals("sanuldap@gmail.com",result.get().getEmail());
        assertEquals("sanu",result.get().getFirstname());
    }

    @Test
    public void testGetUserById_notFound() {
        when(ldapRepository.getLDAPUsersById("404")).thenReturn(Optional.empty());
        Exception exception = assertThrows(LdapUserNotFoundException.class,
                ()-> { ldapUserStrategy.getUserById("404");});
        assertEquals("LDAP user not found with id: 404",exception.getMessage());
    }


}
