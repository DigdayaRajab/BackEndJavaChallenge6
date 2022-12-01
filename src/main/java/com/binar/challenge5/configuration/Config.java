package com.binar.challenge5.configuration;

import com.binar.challenge5.entities.Role;
import com.binar.challenge5.model.enumerations.ERoles;
import com.binar.challenge5.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    Config(RoleRepository roleRepository) {
        LOG.info("Cheking roles presented");
        for(ERoles c : ERoles.values()) {
            try {
                Role roles = roleRepository.findByName(c)
                        .orElseThrow(() -> new RuntimeException("Roles not found"));
                LOG.info("Role {} has been found!", roles.getName());
            } catch(RuntimeException rte) {
                LOG.info("Role {} is not found, inserting to DB . . .", c.name());
                Role roles = new Role();
                roles.setName(c);
                roleRepository.save(roles);
            }
        }
    }

}

