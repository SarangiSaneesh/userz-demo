package com.example.userdemo.config;

import com.example.userdemo.repository.LdapRepository;
import com.example.userdemo.repository.UserRepository;
import com.example.userdemo.service.UserFetchStrategy;
import com.example.userdemo.service.impl.DBUserStrategy;
import com.example.userdemo.service.impl.LdapUserStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {
    @Bean(name = "dbUserStrategy")
    public UserFetchStrategy dbUserStrategy(UserRepository userRepository) {
        return new DBUserStrategy(userRepository);
    }

    @Bean(name = "ldapUserStrategy")
    public UserFetchStrategy ldapUserStrategy(LdapRepository ldapRepository) {
        return new LdapUserStrategy(ldapRepository);
    }

}
