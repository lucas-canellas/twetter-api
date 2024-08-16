package org.lucasdc.auth.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lucasdc.auth.dto.LoginRequest;
import org.lucasdc.auth.dto.SignupRequest;
import org.lucasdc.auth.dto.ValidateResponse;
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
    public Response register(SignupRequest signupRequest) {
        return signupUseCase.register(signupRequest);
    }

    @POST
    @Path("login")
    public Response login(LoginRequest loginRequest) {
        return loginUseCase.login(loginRequest);
    }

    @GET
    @RolesAllowed("User")
    @Path("validate")
    public Response getSecureData() {
        var response = new ValidateResponse();
        response.setMessage("This is secured data accessible by authenticated users");
        return Response.ok(response).build();
    }
}
