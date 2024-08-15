package org.lucasdc.auth.usecases;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.lucasdc.auth.entities.User;
import org.lucasdc.auth.repositories.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class Login {

    @Inject
    UserRepository userRepository;

    @Transactional
    public Response login(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if (foundUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
        }

        boolean matches = BcryptUtil.matches(user.getPassword(), foundUser.getPassword());

        if (!matches) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
        }

        String token = Jwt.issuer("https://example.com/issuer")
                .upn(foundUser.getEmail())
                .groups(new HashSet<>(Collections.singletonList(foundUser.getRole())))
                .sign();

        return Response.ok().entity(token).build();
    }

}
