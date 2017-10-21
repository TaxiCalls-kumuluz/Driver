package com.taxicalls.driver.resources;

import com.taxicalls.driver.model.Trip;
import com.taxicalls.driver.services.BillingService;
import com.taxicalls.driver.services.NotificationService;
import com.taxicalls.driver.services.PassengerService;
import com.taxicalls.driver.services.TripService;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TripsResource {

    @Inject
    private TripService tripService;

    @Inject
    private NotificationService notificationService;
    
    @Inject
    private PassengerService passengerService;
    
    @Inject
    private BillingService billingService;

    private static final Logger LOGGER = Logger.getLogger(TripsResource.class.getName());

    @POST
    public Response acceptTrip(Trip trip) {
        LOGGER.log(Level.INFO, "acceptTrip() invoked");
        notificationService.acceptTrip(trip);
        passengerService.acceptTrip(trip);
        billingService.acceptTrip(trip);
        tripService.acceptTrip(trip);
        return Response.ok().build();
    }
}
