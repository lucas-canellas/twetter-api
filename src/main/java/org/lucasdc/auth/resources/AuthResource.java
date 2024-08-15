package org.lucasdc.auth.resources;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lucasdc.auth.entities.User;

import org.lucasdc.auth.usecases.Login;
import org.lucasdc.auth.usecases.Signup;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    Signup signupUseCase;

    @Inject
    Login loginUseCase;

    @POST
    @Path("register")
    public Response register(User user) {
        return signupUseCase.register(user);
    }

    @POST
    @Path("login")
    public Response login(User user) {
        return loginUseCase.login(user);
    }
}
