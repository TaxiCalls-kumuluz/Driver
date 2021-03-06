package com.taxicalls.driver.resources;

import com.taxicalls.driver.model.Driver;
import com.taxicalls.protocol.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthenticateResource {

    private final EntityManager em;

    public AuthenticateResource() {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<>();
        env.keySet().forEach((envName) -> {
            if (envName.contains("DATABASE_USER")) {
                configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
            } else if (envName.contains("DATABASE_PASS")) {
                configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
            }
        });
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("driver", configOverrides);
        this.em = emf.createEntityManager();
    }

    @POST
    public Response authenticateDriver(Driver driver) {
        Collection<Driver> drivers = em.createNamedQuery("Driver.findAll", Driver.class).getResultList();
        for (Driver stored : drivers) {
            if (stored.getEmail() == null) {
                continue;
            }
            if (stored.getPassword() == null) {
                continue;
            }
            if (stored.getEmail().equals(driver.getEmail()) && stored.getPassword().equals(driver.getPassword())) {
                return Response.successful(stored);
            }
        }
        return Response.notFound();
    }
}
