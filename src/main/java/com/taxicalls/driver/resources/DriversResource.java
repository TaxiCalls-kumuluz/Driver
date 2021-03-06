package com.taxicalls.driver.resources;

import com.taxicalls.driver.model.Driver;
import com.taxicalls.driver.services.TripService;
import com.taxicalls.protocol.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DriversResource {

    private final EntityManager em;

    @Inject
    private TripService tripService;

    public DriversResource() {
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
    public Response createDriver(Driver driver) {
        em.getTransaction().begin();
        em.persist(driver);
        em.getTransaction().commit();
        tripService.createDriver(driver);
        return Response.successful(driver);
    }

    @GET
    public Response getDrivers() {
        Collection<Driver> drivers = em.createNamedQuery("Driver.findAll", Driver.class).getResultList();
        return Response.successful(drivers);
    }

    @GET
    @Path("/{id}")
    public Response getDriver(@PathParam("id") Long id) {
        Driver driver = em.find(Driver.class, id);
        if (driver == null) {
            return Response.notFound();
        }
        return Response.successful(driver);
    }
}
