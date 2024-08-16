package org.lucasdc.auth.usecases;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.lucasdc.auth.dto.SignupRequest;
import org.lucasdc.auth.entities.User;
import org.lucasdc.auth.repositories.UserRepository;

@ApplicationScoped
public class Signup {

    @Inject
    UserRepository userRepository;

    @Transactional
    public Response register(SignupRequest signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Usuário já existe").build();
        }

        var user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());

        String passwordEncrypted = BcryptUtil.bcryptHash(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setRole("User");

        userRepository.persist(user);

        return Response.status(Response.Status.CREATED).entity(user).build();
    }



}
