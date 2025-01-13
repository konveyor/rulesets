package org.konveyor;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Paul Ferraro
 */
@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public class EchoResource {

    @GET
    @Path("{value}")
    public String echo(@PathParam("value") String value) {
        return value;
    }
}

