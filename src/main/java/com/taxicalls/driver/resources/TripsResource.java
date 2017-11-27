package com.taxicalls.driver.resources;

import com.google.gson.Gson;
import com.taxicalls.driver.model.Progress;
import com.taxicalls.driver.model.Trip;
import com.taxicalls.driver.services.BillingService;
import com.taxicalls.driver.services.NotificationService;
import com.taxicalls.driver.services.PassengerService;
import com.taxicalls.driver.services.TripService;
import com.taxicalls.protocol.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
        trip.setProgress(Progress.IN_PROGRESS);
        LOGGER.log(Level.INFO, "tripService.acceptTrip() invoked");
        Gson gson = new Gson();
        Response acceptTrip = tripService.acceptTrip(trip);
        trip = gson.fromJson(acceptTrip.getEntity().toString(), Trip.class);
        LOGGER.log(Level.INFO, "notificationService.acceptTrip() invoked");
        notificationService.acceptTrip(trip);
        LOGGER.log(Level.INFO, "passengerService.acceptTrip() invoked");
        passengerService.acceptTrip(trip);
        LOGGER.log(Level.INFO, "billingService.billingServiceacceptTrip() invoked");
        billingService.acceptTrip(trip);
        LOGGER.log(Level.INFO, "successfully accepted");
        return Response.successful(trip);
    }
}
