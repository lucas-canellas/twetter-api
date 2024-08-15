package org.lucasdc.auth.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.lucasdc.auth.entities.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
